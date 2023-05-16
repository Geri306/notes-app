package com.codecool.notes.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoteIdMismatchException extends Exception {
    // disable stack trace
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public NoteIdMismatchException() {
    }

    public NoteIdMismatchException(String message) {
        super(message);
    }

    public NoteIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoteIdMismatchException(Throwable cause) {
        super(cause);
    }
}
