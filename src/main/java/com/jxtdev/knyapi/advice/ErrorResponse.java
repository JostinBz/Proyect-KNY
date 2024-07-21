package com.jxtdev.knyapi.advice;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ErrorResponse {
    private int statusCode;
    private String message;
    private Map<String, String> errors;
    private LocalDateTime timestamp;

    // Constructor, getters y setters

    public ErrorResponse(int statusCode, String message, Map<String, String> errors, LocalDateTime timestamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.errors = errors;
        this.timestamp = timestamp;
    }

    // Getters y setters
}

