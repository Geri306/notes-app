package com.codecool.notes.api.endpoint;

import com.codecool.notes.api.controller.RegistrationController;
import com.codecool.notes.api.controller.dto.RegistrationDto;
import com.codecool.notes.api.exception.validation.InvalidEmailException;
import com.codecool.notes.api.exception.validation.InvalidPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
@RequiredArgsConstructor
public class RegistrationEndpoint {

    private final RegistrationController registrationController;

    @PostMapping
    void register(@RequestBody RegistrationDto registrationDto) throws InvalidPasswordException, InvalidEmailException {
        System.out.println("registrationDto.toString() = " + registrationDto.toString());
        registrationController.register(registrationDto);
    }
}

