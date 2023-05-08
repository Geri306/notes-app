package com.codecool.api.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
@Configuration
public class ApiExceptionHandler {

    @ExceptionHandler(value = NoteNotFoundException.class)
    public ResponseEntity<Object> handleNoteNotFoundException(
            NoteNotFoundException e
    ) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(
                apiException,
                HttpStatus.NOT_FOUND
        );
    }


    @ExceptionHandler(value = NoteIdMismatchException.class)
    public ResponseEntity<Object> handleNoteNotFoundException(
            NoteIdMismatchException e
    ) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(
                apiException,
                HttpStatus.BAD_REQUEST
        );
    }
}
