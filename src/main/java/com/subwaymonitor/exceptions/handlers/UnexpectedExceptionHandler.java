package com.subwaymonitor.exceptions.handlers;

import com.subwaymonitor.presenters.ErrorPresenter;
import org.jboss.logging.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

@ControllerAdvice
@Order(3)
public class UnexpectedExceptionHandler extends DefaultResponseEntityExceptionHandler {

    private final Logger logger;

    public UnexpectedExceptionHandler() {
        this.logger = Logger.getLogger(this.getClass());
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        this.logger.error(ex);

        String message = this.getMessageFromPropertiesFile(ex.getMessage());

        ErrorPresenter error = new ErrorPresenter("error", message);

        return super.handleExceptionInternal(ex, Arrays.asList(error), new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
