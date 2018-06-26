package com.party.giftzzle.service.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * View Model object for storing the user's key and password.
 */
public class KeyAndPasswordVM {

  @NotBlank
  @NotNull
  private String key;

  @NotBlank
  @NotNull
  private String mobile;

  @NotBlank
  @NotNull
  private String newPassword;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
}
