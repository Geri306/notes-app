package com.gergokovacs.notes.api.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.sasl.AuthenticationException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "LOOL")
public class InvalidEmailException extends AuthenticationException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
