package com.gergokovacs.notes.api.controller;

import com.gergokovacs.notes.api.controller.dto.AuthResponseDto;
import com.gergokovacs.notes.api.controller.dto.UserDetailsRefreshDto;
import com.gergokovacs.notes.logic.authentication.AuthenticationService;
import com.gergokovacs.notes.logic.authentication.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtGenerator jwtGenerator;

    public AuthResponseDto authenticate(Authentication authentication) {
        String roles = authenticationService.getAuthorities(authentication);
        String token = jwtGenerator.generate(authentication);
        return new AuthResponseDto(token, roles);
    }

    public UserDetailsRefreshDto sendLatestUserDetails(Authentication authentication) {
        String roles = authenticationService.getAuthorities(authentication);
        String email = authenticationService.getUsername(authentication);
        return new UserDetailsRefreshDto(roles, email);
    }
}
