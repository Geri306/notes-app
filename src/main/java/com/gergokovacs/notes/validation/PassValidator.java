package com.gergokovacs.notes.validation;

import com.gergokovacs.notes.api.exception.validation.InvalidPasswordException;
import org.passay.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PassValidator {

    public void validate(final String password) throws InvalidPasswordException {

        final PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new CharacterRule(EnglishCharacterData.Alphabetical, 1),
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),
                new IllegalSequenceRule(EnglishSequenceData.USQwerty, 5, false),
                new WhitespaceRule()));

        final RuleResult result = validator.validate(new PasswordData(password));

        if (!result.isValid()) {
            throw new InvalidPasswordException("weak password");
        }
    }
}