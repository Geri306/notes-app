package com.codecool.notes.api.endpoint;

import com.codecool.notes.api.controller.dto.LoginRole;
import com.codecool.notes.data.Role;
import com.codecool.notes.security.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginEndpoint {

    private final AuthenticationService authenticationService;

    @GetMapping
    public LoginRole checkAdminRole() {
        return authenticationService.checkAdminRole();

    }
}
