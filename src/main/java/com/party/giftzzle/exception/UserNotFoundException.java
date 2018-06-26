package com.party.giftzzle.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class UserNotFoundException extends AbstractThrowableProblem {

  public UserNotFoundException() {
    super(ErrorConstants.USER_NOT_FOUND_USED_TYPE, "User not found ", Status.BAD_REQUEST);
  }
}
