package com.gergokovacs.notes.api.controller;

import com.gergokovacs.notes.api.controller.dto.RegistrationRequestDto;
import com.gergokovacs.notes.api.exception.validation.InvalidEmailException;
import com.gergokovacs.notes.api.exception.validation.InvalidPasswordException;
import com.gergokovacs.notes.logic.formatting.Decoder;
import com.gergokovacs.notes.logic.formatting.Splitter;
import com.gergokovacs.notes.logic.registration.RegistrationService;
import com.gergokovacs.notes.logic.registration.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final ValidationService validationService;
    private final RegistrationService registrationService;
    private final Splitter splitter;
    private final Decoder decoder;

    public void register(RegistrationRequestDto registrationRequestDto, boolean asAdmin) throws InvalidEmailException, InvalidPasswordException {
        String decoded = decoder.format(registrationRequestDto.encodedAuth());
        Map<String, String> credentials = splitter.split(decoded);
        validationService.validate(credentials);
        registrationService.register(credentials, asAdmin);
    }
}
