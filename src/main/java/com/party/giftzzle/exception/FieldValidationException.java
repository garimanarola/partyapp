package com.party.giftzzle.exception;

import java.net.URI;

public class FieldValidationException extends BadRequestAlertException {

  public FieldValidationException(URI uri, String message, String title) {
    super(uri, message, "userManagement", title);
  }

  public FieldValidationException(URI uri, String message, String entityName, String title) {
    super(uri, message, entityName, title);
  }

}
