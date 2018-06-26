package com.party.giftzzle.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

/**
 * Created by grahul on 10-05-2018.
 */
public class InvalidOTPException extends AbstractThrowableProblem {
  public InvalidOTPException() {
    super(ErrorConstants.OTP_INVALID_TYPE, "Invalid Otp entered", Status.BAD_REQUEST);
  }
}
