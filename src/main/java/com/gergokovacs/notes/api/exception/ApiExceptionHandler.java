package com.gergokovacs.notes.api.exception;

import com.gergokovacs.notes.api.exception.note.NoteIdMismatchException;
import com.gergokovacs.notes.api.exception.note.NoteNotFoundException;
import com.gergokovacs.notes.api.exception.validation.InvalidEmailException;
import com.gergokovacs.notes.api.exception.validation.InvalidPasswordException;
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

    @ExceptionHandler(value = InvalidEmailException.class)
    public ResponseEntity<Object> invalidEmailExceptionException(
            InvalidEmailException e
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

    @ExceptionHandler(value = InvalidPasswordException.class)
    public ResponseEntity<Object> invalidPasswordException(
            InvalidPasswordException e
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
