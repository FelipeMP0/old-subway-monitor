package com.subwaymonitor.exceptions.handlers;

import com.subwaymonitor.config.ValidationMessagesReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class DefaultResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired private ValidationMessagesReader messagesReader;

  protected String getMessageFromPropertiesFile(String key) {
    return this.messagesReader.getProperty(key);
  }
}
