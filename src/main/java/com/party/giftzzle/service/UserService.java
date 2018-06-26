package com.party.giftzzle.service;

import com.party.giftzzle.domain.AdminInfo;
import com.party.giftzzle.domain.Authority;
import com.party.giftzzle.domain.User;
import com.party.giftzzle.exception.AccountInactiveException;
import com.party.giftzzle.exception.InternalServerErrorException;
import com.party.giftzzle.exception.MobileNotFoundException;
import com.party.giftzzle.repository.AdminInfoRepository;
import com.party.giftzzle.repository.AuthorityRepository;
import com.party.giftzzle.repository.UserRepository;
import com.party.giftzzle.security.AuthoritiesConstants;
import com.party.giftzzle.security.SecurityUtils;
import com.party.giftzzle.service.dto.AdminInfoDTO;
import com.party.giftzzle.service.dto.KeyAndPasswordVM;
import com.party.giftzzle.service.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
@Transactional()
public class UserService {

  private final Logger log = LoggerFactory.getLogger(UserService.class);

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final MailService mailService;
  private final AuthorityRepository authorityRepository;
  private final AdminInfoRepository adminInfoRepository;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, MailService mailService, AuthorityRepository authorityRepository, AdminInfoRepository adminInfoRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.mailService = mailService;
    this.authorityRepository = authorityRepository;
    this.adminInfoRepository = adminInfoRepository;
  }

  public Optional<User> activateRegistration(String key) {
    log.debug("Activating user for activation key {}", key);
    return userRepository.findOneByActivationKey(key)
        .map(user -> {
          // activate given user for the registration key.
          user.setActivated(true);
          user.setActivationKey(null);
//          userSearchRepository.save(user);
          log.debug("Activated user: {}", user);
          return user;
        });
  }

  public boolean completePasswordReset(KeyAndPasswordVM keyAndPasswordVM) {
    log.debug("Reset user password for otp key {}", keyAndPasswordVM.getKey());
    Optional<User> userOptional = null;
    if (!StringUtils.isEmpty(keyAndPasswordVM.getMobile())) {
      userOptional = userRepository.findOneByMobile(keyAndPasswordVM.getMobile());
    }

    /**
     * Invalid Mobile
     */
    if (userOptional == null || !userOptional.isPresent()) {
      throw new InternalServerErrorException("No user was found for this login");
    }
    /**
     * User Inactive
     */
    else if (!userOptional.get().getActivated()) {
      throw new AccountInactiveException();
    }
    /**
     * Valid Mobile and OTP
     */
    else if (keyAndPasswordVM.getKey().equals(userOptional.get().getMobileKey())) {
      userOptional.get().setPassword(passwordEncoder.encode(keyAndPasswordVM.getNewPassword()));
      userOptional.get().setMobileKey(null);
      userOptional.get().setResetDate(null);
      userOptional.get().setActivated(true);
      userOptional.get().setResetAttempt(0);
      return true;
    }
    /**
     * Invalid OTP
     */
    else {
      userOptional.get().setActivated(true);
      Integer attemptCount = userOptional.get().getResetAttempt();
      if (attemptCount >= 2) {
        userOptional.get().setActivated(false);
      }
      userOptional.get().setResetDate(new Date());
      userOptional.get().setResetAttempt(attemptCount + 1);
      return false;
    }
  }

  public Optional<User> requestPasswordReset(String mobile) {
    Optional<User> userOptional = userRepository.findOneByMobile(mobile);
    if (!userOptional.isPresent()) {
      throw new MobileNotFoundException();
    } else if (!userOptional.get().getActivated()) {
      throw new AccountInactiveException();
    } else {
      User user = userOptional.get();
      user.setResetAttempt(0);
      user.setResetDate(new Date());
      user.setMobileKey(RandomUtil.generateKeyWithSize(6));

      /**
       * Message Sending Login
       */

      /**
       * Mail Sending Login
       */
      if (user.getEmail() != null && "Y".equals(user.getIsEmailVerified())) {
        mailService.sendPasswordResetOtp(user);
      }

      return userOptional;
    }
  }

  public void changePassword(String password) {
    SecurityUtils.getCurrentUserLogin()
        .flatMap(userRepository::findOneByLogin)
        .ifPresent(user -> {
          String encryptedPassword = passwordEncoder.encode(password);
          user.setPassword(encryptedPassword);
          log.debug("Changed password for User: {}", user);
        });
  }

  @Transactional(readOnly = true)
  public Optional<User> getUserWithAuthorities() {
    return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
  }

  public AdminInfoDTO createUser(AdminInfoDTO userDTO) throws ParseException {
    User user = new User();
    AdminInfo adminInfo = null;
    String loginRole = null;

    user.setLogin(userDTO.getLogin());
    user.setFirstName(userDTO.getFirstName());
    user.setLastName(userDTO.getLastName());
    user.setEmail(userDTO.getEmail());
    user.setMobile(userDTO.getMobile());
    user.setIsMobileVerified("N");
    user.setIsEmailVerified("N");
    user.setResetAttempt(0);

    if (userDTO.getAuthorities() != null) {

      Set<Authority> authorities = userDTO.getAuthorities().stream()
              .map(authorityRepository::findOne)
              .collect(Collectors.toSet());
      user.setAuthorities(authorities);

      /**
       * New User Role as Admin
       */
      if (SecurityUtils.isAdmin() && AuthoritiesConstants.ADMIN.equals(authorities.stream().findFirst().get().getName())) {
        loginRole = AuthoritiesConstants.ADMIN;
        adminInfo = new AdminInfo(userDTO);
        Optional<User> userWithAuthorities = this.getUserWithAuthorities();
        Long loginId = 0L;
        if (userWithAuthorities.isPresent()) {
          loginId = userWithAuthorities.get().getId();
        }
        user.setParentId(loginId);
        user.setCreatedBy(String.valueOf(loginId));
      }
    }
    String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
    user.setPassword(encryptedPassword);
    user.setResetKey(RandomUtil.generateResetKey());
    userDTO.setResetKey(user.getResetKey());
    user.setActivated(true);
    userRepository.save(user);
    //userSearchRepository.save(user);

    if (loginRole != null && loginRole.equals(AuthoritiesConstants.ADMIN)) {
      adminInfo.setUserId(user.getId());
      adminInfoRepository.save(adminInfo);
      //adminInfoSearchRepository.save(adminInfo);
    }
    userDTO.setId(user.getId());
    log.debug("Created Information for User: {}", user);
    return userDTO;
  }
}
