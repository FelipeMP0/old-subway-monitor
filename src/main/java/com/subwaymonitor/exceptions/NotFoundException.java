package com.subwaymonitor.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception {

  private HttpStatus status = HttpStatus.NOT_FOUND;
  private String field;

  public NotFoundException() {}

  public NotFoundException(Throwable e) {
    super(e);
  }

  public NotFoundException(String field, String message) {
    super(message);

    this.field = field;
  }

  public NotFoundException(String field, String message, HttpStatus status) {
    super(message);

    this.status = status;
    this.field = field;
  }

  public String getField() {
    return field;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
