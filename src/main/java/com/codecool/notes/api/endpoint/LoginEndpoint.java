package com.codecool.notes.api.endpoint;

import com.codecool.notes.api.controller.dto.LoginResponseDto;
import com.codecool.notes.api.controller.dto.UserCredentialsDto;
import com.codecool.notes.security.TokenGenerator;
import com.codecool.notes.security.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginEndpoint {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final TokenGenerator tokenGenerator;

//    @GetMapping
//    public LoginResponseDto checkAdminRole() {
//        return authenticationService.getRoles();
//
//    }

//    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserCredentialsDto credentials) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = tokenGenerator.generateSessionToken(32);
            String encodedAuth = Base64.getEncoder().encodeToString((userDetails.getUsername() + ":" + userDetails.getPassword()).getBytes());
            LoginResponseDto body = new LoginResponseDto(
                    userDetails.getUsername(),
                    authenticationService.getRoles(),
                    encodedAuth
                    );
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Access-Control-Allow-Origin", "asd");
//            return ResponseEntity.ok().headers(headers).body(body);
            return ResponseEntity.ok().body(body);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("wrong email or password");
//            throw new BadCredentialsException("wrong email or password");
        }
    }
}
