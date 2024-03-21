package com.shop.soap_server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Set;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
    private Set<String> validationError;

    public Set<String> getValidationError() {
        return validationError;
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Set<String> validationError) {
        super(message);
        this.validationError = validationError;

    }
}

