package com.codecool.notes.api.exception.note;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoteIdMismatchException extends Exception {
    // disable stack trace
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public NoteIdMismatchException(String message) {
        super(message);
    }
}
