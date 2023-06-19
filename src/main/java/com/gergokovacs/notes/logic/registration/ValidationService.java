package com.gergokovacs.notes.logic.registration;

import com.gergokovacs.notes.api.exception.validation.InvalidEmailException;
import com.gergokovacs.notes.api.exception.validation.InvalidPasswordException;
import com.gergokovacs.notes.validation.EmailValidator;
import com.gergokovacs.notes.validation.PassValidator;
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
