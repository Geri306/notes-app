package com.gergokovacs.notes.logic.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthenticationService {
    public String getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }

    public String getUsername(Authentication authentication) {
        return authentication.getName();
    }
}
