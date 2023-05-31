package com.codecool.notes.validation;

import com.codecool.notes.api.exception.validation.InvalidEmailException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    public void validate(final String email) throws InvalidEmailException {
        Matcher matcher = PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidEmailException("invalid email address");
        }
    }
}