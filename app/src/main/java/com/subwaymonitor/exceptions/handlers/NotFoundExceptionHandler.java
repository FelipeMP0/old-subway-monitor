package com.subwaymonitor.exceptions.handlers;

import com.subwaymonitor.exceptions.NotFoundException;
import com.subwaymonitor.presenters.ErrorPresenter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

@ControllerAdvice
@Order(2)
public class NotFoundExceptionHandler extends DefaultResponseEntityExceptionHandler {

  @ExceptionHandler(value = {NotFoundException.class})
  protected ResponseEntity<Object> handleConflict(NotFoundException ex, WebRequest request) {
    String message = this.getMessageFromPropertiesFile(ex.getMessage());

    ErrorPresenter error = new ErrorPresenter(ex.getField(), message);

    return super.handleExceptionInternal(
        ex, Arrays.asList(error), new HttpHeaders(), ex.getStatus(), request);
  }
}
