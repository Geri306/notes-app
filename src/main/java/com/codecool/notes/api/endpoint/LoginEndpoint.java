package com.codecool.notes.api.endpoint;

import com.codecool.notes.api.controller.LoginController;
import com.codecool.notes.api.controller.dto.LoginResponseDto;
import com.codecool.notes.security.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginEndpoint {

    private final LoginController loginController;
    private final AuthenticationService authenticationService;


    @GetMapping
    public LoginResponseDto login(@RequestHeader(value = "Authorization") String encodedCredentials) {
        return loginController.login(encodedCredentials);
    }
}
