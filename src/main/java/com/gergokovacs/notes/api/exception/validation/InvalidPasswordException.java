package com.gergokovacs.notes.api.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.sasl.AuthenticationException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPasswordException extends AuthenticationException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
