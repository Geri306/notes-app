package com.codecool.notes.api.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends Exception {

    public EmailAlreadyExistsException(final String message) {
        super(message);
    }

}
