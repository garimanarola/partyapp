package com.party.giftzzle.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class MobileNotFoundException extends AbstractThrowableProblem {

  public MobileNotFoundException() {
    super(ErrorConstants.MOBILE_NOT_FOUND_TYPE, "Mobile not registered", Status.BAD_REQUEST);
  }
}
