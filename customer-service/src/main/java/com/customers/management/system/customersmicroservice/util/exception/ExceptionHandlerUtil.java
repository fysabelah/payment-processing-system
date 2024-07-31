package com.customers.management.system.customersmicroservice.util.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerUtil {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<StandardError> genericError(Exception e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status)
                .body(new StandardError(status.value(),
                        "Unknown Error",
                        e.getMessage(),
                        request.getRequestURI()));
    }

    @ExceptionHandler(ValidationsException.class)
    public ResponseEntity<StandardError> validationsError(ValidationsException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError(status.value(),
                "",
                request.getRequestURI(),
                e.getMessage());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<StandardError> notFound(NoSuchElementException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError error = new StandardError(status.value(),
                "Not Found",
                request.getRequestURI(),
                e.getMessage()
        );

        return ResponseEntity.status(status).body(error);
    }
}
