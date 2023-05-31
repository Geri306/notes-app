package com.codecool.notes.api.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "LOOL")
public class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}
