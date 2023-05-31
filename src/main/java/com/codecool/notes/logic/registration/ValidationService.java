package com.codecool.notes.logic.registration;

import com.codecool.notes.api.exception.validation.InvalidEmailException;
import com.codecool.notes.api.exception.validation.InvalidPasswordException;
import com.codecool.notes.validation.EmailValidator;
import com.codecool.notes.validation.PassValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final EmailValidator emailValidator;
    private final PassValidator passValidator;

    public void validate(Map<String, String> credentials) throws InvalidEmailException, InvalidPasswordException {
        emailValidator.validate(credentials.get("email"));
        passValidator.validate(credentials.get("password"));
    }
}
