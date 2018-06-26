package com.party.giftzzle.exception;

import java.net.URI;

public final class ErrorConstants {

  public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
  public static final String ERR_VALIDATION = "error.validation";
  public static final String PROBLEM_BASE_URL = "http://www.jhipster.tech/problem";
  public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
  public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");
  public static final URI PARAMETERIZED_TYPE = URI.create(PROBLEM_BASE_URL + "/parameterized");
  public static final URI INVALID_PASSWORD_TYPE = URI.create(PROBLEM_BASE_URL + "/invalid-password");
  public static final URI EMAIL_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/email-already-used");
  public static final URI LOGIN_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/login-already-used");
  public static final URI EMAIL_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/email-not-found");
  public static final URI MOBILE_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/mobile-not-found");
  public static final URI MOBILE_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/mobile-already-used");
  public static final URI USER_NOT_FOUND_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/user-not-found");
  public static final URI INVALID_FILE_UPLOAD = URI.create(PROBLEM_BASE_URL + "/invalid-file-upload");
  public static final URI ACCOUNT_INACTIVE_TYPE = URI.create(PROBLEM_BASE_URL + "/account-inactive");
  public static final URI OTP_INVALID_TYPE = URI.create(PROBLEM_BASE_URL + "/otp-invalid");
  public static final String ERR_FILE_SIZE_FAILURE = "Maximum file size allowed are 1024KB";

  private ErrorConstants() {
  }
}
