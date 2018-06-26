package com.party.giftzzle.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.party.giftzzle.config.Constants;
import com.party.giftzzle.domain.Authority;
import com.party.giftzzle.domain.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

  private final Logger log = LoggerFactory.getLogger(UserDTO.class);

  private Long id;

  @NotBlank
  @Pattern(regexp = Constants.LOGIN_REGEX)
  @Size(min = 1, max = 50)
  private String login;

  @NotNull
  @NotBlank
  @Size(max = 50)
  private String firstName;

  @NotNull
  @NotBlank
  @Size(min = 5, max = 20)
  private String password;

  @Size(max = 50)
  private String lastName;

  @Email
  @Size(min = 5, max = 100)
  private String email;

  @NotNull
  @NotBlank
  @Pattern(regexp = Constants.MOBILE_REGEX)
  @Size(min = 10, max = 10)
  private String mobile;

  @JsonIgnore
  private String isMobileVerified = "N";

  @JsonIgnore
  private String isEmailVerified = "N";

  @JsonIgnore
  private boolean activated = true;

  @CreatedBy
  @JsonIgnore
  private String createdBy;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @JsonIgnore
  private Date createdDate;

  @LastModifiedBy
  @JsonIgnore
  private String lastModifiedBy;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @JsonIgnore
  private Date lastModifiedDate;

  private Set<String> authorities;

  public UserDTO() {
    // Empty constructor needed for Jackson.
  }


  public UserDTO(User user) {
    this.id = user.getId();
    this.login = user.getLogin();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.email = user.getEmail();
    this.mobile = user.getMobile();
    this.isEmailVerified = user.getIsEmailVerified();
    this.isMobileVerified = user.getIsMobileVerified();
    this.activated = user.getActivated();
    this.createdBy = user.getCreatedBy();
    this.createdDate = user.getCreatedDate();
    this.lastModifiedBy = user.getLastModifiedBy();
    this.lastModifiedDate = user.getLastModifiedDate();
    this.authorities = user.getAuthorities().stream()
        .map(Authority::getName)
        .collect(Collectors.toSet());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isActivated() {
    return activated;
  }

  public void setActivated(boolean activated) {
    this.activated = activated;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public Set<String> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Set<String> authorities) {
    this.authorities = authorities;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getIsMobileVerified() {
    return isMobileVerified;
  }

  public void setIsMobileVerified(String isMobileVerified) {
    this.isMobileVerified = isMobileVerified;
  }

  public String getIsEmailVerified() {
    return isEmailVerified;
  }

  public void setIsEmailVerified(String isEmailVerified) {
    this.isEmailVerified = isEmailVerified;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "UserDTO{" +
        "login='" + login + '\'' +
        ", mobile='" + mobile + '\'' +
        ", isEmailVerified='" + isEmailVerified + '\'' +
        ", isMobileVerified='" + isMobileVerified + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", activated=" + activated +
        ", createdBy=" + createdBy +
        ", createdDate=" + createdDate +
        ", lastModifiedBy='" + lastModifiedBy + '\'' +
        ", lastModifiedDate=" + lastModifiedDate +
        ", authorities=" + authorities +
        "}";
  }
}
