package com.party.giftzzle.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class AccountInactiveException extends AbstractThrowableProblem {

  public AccountInactiveException() {
    super(ErrorConstants.ACCOUNT_INACTIVE_TYPE, "Your account is blocked", Status.BAD_REQUEST);
  }
}
