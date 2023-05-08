package com.codecool.api.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

// this is what the client will see
@ToString
@Getter
public class ApiException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;

    public ApiException(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }
}
