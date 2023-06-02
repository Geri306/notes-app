package com.codecool.notes.api.controller;

import com.codecool.notes.api.controller.dto.RegistrationDto;
import com.codecool.notes.api.exception.validation.InvalidEmailException;
import com.codecool.notes.api.exception.validation.InvalidPasswordException;
import com.codecool.notes.logic.formatting.Decoder;
import com.codecool.notes.logic.formatting.Splitter;
import com.codecool.notes.logic.registration.RegistrationService;
import com.codecool.notes.logic.registration.ValidationService;
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

    public void register(RegistrationDto registrationDto, boolean asAdmin) throws InvalidEmailException, InvalidPasswordException {
        String decoded = decoder.format(registrationDto.encodedAuth());
        Map<String, String> credentials = splitter.split(decoded);
        validationService.validate(credentials);
        registrationService.register(credentials, asAdmin);
    }
}
