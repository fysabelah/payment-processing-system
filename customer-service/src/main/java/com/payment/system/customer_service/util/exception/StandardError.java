package com.payment.system.customer_service.util.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StandardError {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", locale = "pt_BR")
    private LocalDateTime time;

    private int statusCode;

    private String error;

    private String message;

    private String path;

    public StandardError(int statusCode, String error, String message, String path) {
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.path = path;
        this.time = LocalDateTime.now();
    }
}
