package com.party.giftzzle.controller;

import com.party.giftzzle.controller.util.HeaderUtil;
import com.party.giftzzle.domain.User;
import com.party.giftzzle.exception.BadRequestAlertException;
import com.party.giftzzle.exception.EmailAlreadyUsedException;
import com.party.giftzzle.exception.LoginAlreadyUsedException;
import com.party.giftzzle.exception.MobileAlreadyUsedException;
import com.party.giftzzle.repository.UserRepository;
import com.party.giftzzle.security.AuthoritiesConstants;
import com.party.giftzzle.service.MailService;
import com.party.giftzzle.service.UserService;
import com.party.giftzzle.service.dto.AdminInfoDTO;
import com.party.giftzzle.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.test.annotation.Timed;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.LocalDate;


/**
 * Created by agarima on 20-06-2018.
 */

@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final UserRepository userRepository;

    private final UserService userService;

    private final MailService mailService;

    public UserResource(UserRepository userRepository, UserService userService, MailService mailService) {

        this.userRepository = userRepository;
        this.userService = userService;
        this.mailService = mailService;
    }

    /**
     * POST  /users  : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends an
     * mail with an activation link.
     * The user needs to be activated on creation.
     *
     * @param userDTO the user to create
     * @return the ResponseEntity with status 201 (Created) and with body the new user, or with status 400 (Bad Request) if the login or email is already in use
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws BadRequestAlertException 400 (Bad Request) if the login or email is already in use
     */
    @PostMapping("/users")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<AdminInfoDTO> createUser(@Valid @RequestBody AdminInfoDTO userDTO) throws URISyntaxException, ParseException {
        log.debug("REST request to save User : {}", userDTO);

        if (userDTO.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
            // Lowercase the user login before comparing with database
        } else if (userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).isPresent()) {
            throw new LoginAlreadyUsedException();
        } else if (!StringUtils.isEmpty(userDTO.getMobile()) && userRepository.findOneByMobile(userDTO.getMobile()).isPresent()) {
            throw new MobileAlreadyUsedException();
        } else if (!StringUtils.isEmpty(userDTO.getEmail()) && userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        } else {
            AdminInfoDTO newUser = userService.createUser(userDTO);

            /**
             * Account Creation Mail - Admin
             */
            if (userDTO.getEmail() != null && !"".equals(userDTO.getEmail())) {
                User user = new User();
                user.setLogin(newUser.getLogin());
                user.setEmail(newUser.getEmail());
                user.setResetKey(newUser.getResetKey());
                mailService.sendCreationEmail(user);
            }
            /**
             * Account Creation Mobile SMS - Admin
             */

            return ResponseEntity.created(new URI("/api/admin/users/" + newUser.getLogin()))
                    .headers(HeaderUtil.createAlert("userManagement.created", newUser.getLogin()))
                    .body(newUser);
        }
    }
}
