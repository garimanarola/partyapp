package com.party.giftzzle.exception;

public class MobileAlreadyUsedException extends BadRequestAlertException {

  public MobileAlreadyUsedException() {
    super(ErrorConstants.MOBILE_ALREADY_USED_TYPE, "Mobile no already in use", "userManagement", "mobileexists");
  }
}
