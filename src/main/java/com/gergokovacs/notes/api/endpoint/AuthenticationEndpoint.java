package com.gergokovacs.notes.api.endpoint;

import com.gergokovacs.notes.api.controller.AuthenticationController;
import com.gergokovacs.notes.api.controller.dto.AuthResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class AuthenticationEndpoint {

    private final AuthenticationController authenticationController;

    @PostMapping
    AuthResponseDto login(Authentication authentication) {
        return authenticationController.authenticate(authentication);
    }
}
