package com.gergokovacs.notes.api.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.sasl.AuthenticationException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailNotFoundException extends AuthenticationException {

    public EmailNotFoundException(final String message) {
        super(message);
    }

}
