package com.codecool.notes.api.controller;

import com.codecool.notes.api.controller.dto.LoginResponseDto;
import com.codecool.notes.logic.formatting.Splitter;
import com.codecool.notes.logic.formatting.StringFormatterApplicator;
import com.codecool.notes.logic.login.DtoService;
import com.codecool.notes.security.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationService authenticationService;
    private final Splitter splitter;
    private final DtoService dtoService;
    private final StringFormatterApplicator stringFormatterApplicator;

    public LoginResponseDto login(String authHeader) {
        String decodedAuthHeader = stringFormatterApplicator.applyFormatters(authHeader);
        Map<String, String> credentials = splitter.split(decodedAuthHeader);
        UserDetails userDetails = authenticationService.authenticate(credentials);
        Set<String> roles = authenticationService.getRoles();
        return dtoService.instantiate(userDetails.getUsername(), roles);
    }
}
