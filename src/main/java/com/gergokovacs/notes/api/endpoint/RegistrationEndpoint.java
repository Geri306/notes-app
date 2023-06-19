package com.gergokovacs.notes.api.endpoint;

import com.gergokovacs.notes.api.controller.RegistrationController;
import com.gergokovacs.notes.api.controller.dto.RegistrationRequestDto;
import com.gergokovacs.notes.api.exception.validation.InvalidEmailException;
import com.gergokovacs.notes.api.exception.validation.InvalidPasswordException;
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
    void register(@RequestBody RegistrationRequestDto registrationRequestDto) throws InvalidPasswordException, InvalidEmailException {
        registrationController.register(registrationRequestDto, registrationRequestDto.asAdmin());
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("admin")
    void registerAdmin(@RequestBody RegistrationRequestDto registrationRequestDto) throws InvalidPasswordException, InvalidEmailException {
        registrationController.register(registrationRequestDto, registrationRequestDto.asAdmin());
    }
}

