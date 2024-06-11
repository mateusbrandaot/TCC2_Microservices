package com.agenday.usermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
