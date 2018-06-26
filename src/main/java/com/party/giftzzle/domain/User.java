package com.party.giftzzle.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.party.giftzzle.config.Constants;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

/**
 * A user.
 */
@Entity
@Table(name = "user")

public class User extends AbstractAuditingEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Pattern(regexp = Constants.LOGIN_REGEX)
  @Size(min = 1, max = 50)
  @Column(length = 50, unique = true, nullable = false)
  private String login;

  @JsonIgnore
  @NotNull
  @Column(name = "password_hash", length = 60)
  private String password;

  @Size(max = 50)
  @Column(name = "first_name", length = 50)
  private String firstName;

  @Size(max = 50)
  @Column(name = "last_name", length = 50)
  private String lastName;

  @Email
  @Size(min = 5, max = 100)
  @Column(length = 100, unique = true)
  private String email;

  @NotNull
  @NotBlank
  @Pattern(regexp = Constants.MOBILE_REGEX)
  @Size(min = 10, max = 10)
  @Column(length = 10, unique = true)
  private String mobile;

  @JsonIgnore
  private Long parentId;

  @JsonIgnore
  private String mobileKey;

  @JsonIgnore
  private Integer resetAttempt;

  @Size(min = 1, max = 1)
  @Column(name = "is_mobile_verified", columnDefinition = "VARCHAR(1) default 'N'")
  private String isMobileVerified;

  @Size(min = 1, max = 1)
  @Column(name = "is_email_verified", columnDefinition = "VARCHAR(1) default 'N'")
  private String isEmailVerified;

  @Column(nullable = false)
  private boolean activated = false;

  @Size(min = 2, max = 6)
  @Column(name = "lang_key", length = 6)
  private String langKey;

  @Size(max = 20)
  @Column(name = "activation_key", length = 20)
  @JsonIgnore
  private String activationKey;

  @Size(max = 20)
  @Column(name = "reset_key", length = 20)
  @JsonIgnore
  private String resetKey;

  @JsonIgnore
  @Column(name = "reset_date")
  private Date resetDate = null;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
      name = "user_authority",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})

  @BatchSize(size = 20)
  private Set<Authority> authorities = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  // Lowercase the login before saving it in database
  public void setLogin(String login) {
    this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public boolean getActivated() {
    return activated;
  }

  public void setActivated(boolean activated) {
    this.activated = activated;
  }

  public String getActivationKey() {
    return activationKey;
  }

  public void setActivationKey(String activationKey) {
    this.activationKey = activationKey;
  }

  public String getResetKey() {
    return resetKey;
  }

  public void setResetKey(String resetKey) {
    this.resetKey = resetKey;
  }

  public Date getResetDate() {
    return resetDate;
  }

  public void setResetDate(Date resetDate) {
    this.resetDate = resetDate;
  }

  public String getLangKey() {
    return langKey;
  }

  public void setLangKey(String langKey) {
    this.langKey = langKey;
  }

  public Set<Authority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Set<Authority> authorities) {
    this.authorities = authorities;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public String getMobileKey() {
    return mobileKey;
  }

  public void setMobileKey(String mobileKey) {
    this.mobileKey = mobileKey;
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


  public User() {
  }

  public Integer getResetAttempt() {
    return resetAttempt;
  }

  public void setResetAttempt(Integer resetAttempt) {
    this.resetAttempt = resetAttempt;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    User user = (User) o;
    return !(user.getId() == null || getId() == null) && Objects.equals(getId(), user.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "User{" +
        "login='" + login + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", activated='" + activated + '\'' +
        ", langKey='" + langKey + '\'' +
        ", activationKey='" + activationKey + '\'' +
        "}";
  }
}
