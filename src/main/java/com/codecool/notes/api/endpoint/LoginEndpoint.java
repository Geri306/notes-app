package com.codecool.notes.api.endpoint;

import com.codecool.notes.api.controller.dto.LoginResponseDto;
import com.codecool.notes.security.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginEndpoint {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public ResponseEntity<?> login(@RequestHeader(value = "Authorization") String authorizationHeader) {
        String encodedCredentials = authorizationHeader.substring("Basic ".length());
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String decodedCredentials = new String(decodedBytes);
        String[] credentials = decodedCredentials.split(":");
        String email = credentials[0];
        String password = credentials[1];
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            LoginResponseDto body = new LoginResponseDto(
                    userDetails.getUsername(),
                    authenticationService.getRoles(),
                    encodedCredentials
                    );
            return ResponseEntity.ok().body(body);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("wrong email or password");
        }
    }
}
