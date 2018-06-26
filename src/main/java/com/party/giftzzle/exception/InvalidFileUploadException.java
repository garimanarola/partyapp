package com.party.giftzzle.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

/**
 * Created by grahul on 10-05-2018.
 */
public class InvalidFileUploadException extends AbstractThrowableProblem {
  public InvalidFileUploadException() {
    super(ErrorConstants.INVALID_FILE_UPLOAD, "Invalid File Upload", Status.BAD_REQUEST);
  }
}
