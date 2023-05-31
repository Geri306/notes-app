package com.codecool.notes.api.endpoint;

import com.codecool.notes.api.controller.dto.LoginRole;
import com.codecool.notes.api.controller.dto.UserCredentials;
import com.codecool.notes.data.Role;
import com.codecool.notes.security.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public String login(@RequestBody UserCredentials credentials) {
        // Perform authentication logic here (e.g., verify username and password)
        // ...

        // Create authentication token
        UserDetails userDetails = ...; // Retrieve user details from database
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Return response with user details (e.g., username)
        return userDetails.getUsername();
    }


}
