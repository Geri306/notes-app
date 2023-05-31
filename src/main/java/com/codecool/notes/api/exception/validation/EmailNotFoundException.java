package com.codecool.notes.api.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailNotFoundException extends Exception {

    public EmailNotFoundException(final String message) {
        super(message);
    }

}
