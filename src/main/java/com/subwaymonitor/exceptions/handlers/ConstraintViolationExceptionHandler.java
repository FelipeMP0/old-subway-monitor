package com.subwaymonitor.exceptions.handlers;

import com.subwaymonitor.presenters.ErrorPresenter;
import org.jboss.logging.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Order(1)
@ControllerAdvice
public class ConstraintViolationExceptionHandler extends ResponseEntityExceptionHandler {

  private final Logger logger;

  public ConstraintViolationExceptionHandler() {
    this.logger = Logger.getLogger(this.getClass());
  }

  public static ResponseEntity<Object> handleBindingResultErrors(
      BindingResult bindingResult, HttpHeaders headers, HttpStatus status) {
    if (bindingResult != null) {
      List<ErrorPresenter> errors = new ArrayList<>();

      List<FieldError> fieldErrors = bindingResult.getFieldErrors();

      for (FieldError fieldError : fieldErrors) {
        errors.add(new ErrorPresenter(fieldError.getField(), fieldError.getDefaultMessage()));
      }

      return new ResponseEntity<>(errors, headers, status);
    }

    return null;
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    this.logger.error("ConstraintViolationExceptionHandler", ex);

    return handleBindingResultErrors(ex.getBindingResult(), headers, status);
  }
}
