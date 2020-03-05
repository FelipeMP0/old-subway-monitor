package com.subwaymonitor.appcommon.exceptions;

public class DatabaseException extends RuntimeException {

  public DatabaseException(Throwable cause) {
    super("unexpected.error", cause);
  }

  public DatabaseException(String message, Throwable cause) {
    super(message, cause);
  }
}
