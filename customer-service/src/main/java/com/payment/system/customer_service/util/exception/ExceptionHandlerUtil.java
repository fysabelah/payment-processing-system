package com.payment.system.customer_service.util.exception;

import com.payment.system.customer_service.util.MessageUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
                        "Erro desconhecido",
                        e.getMessage(),
                        request.getRequestURI()));
    }

    @ExceptionHandler(ValidationsException.class)
    public ResponseEntity<StandardError> validationsError(ValidationsException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError(status.value(),
                "Bloqueado por regra de negócio",
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<StandardError> notFound(NoSuchElementException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError error = new StandardError(status.value(),
                "Não encontrado",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<StandardError> dataValidations(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        String message;

        if (e.getFieldError() != null && e.getFieldError().getDefaultMessage() != null) {
            message = MessageUtil.getMessage(e.getFieldError().getDefaultMessage());

            if (message == null) {
                message = e.getFieldError().getField() + ": " + e.getFieldError().getDefaultMessage();
            }
        } else {
            message = e.getMessage();
        }

        StandardError error = new StandardError(status.value(),
                "Possível erro de payload",
                message,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }
}
