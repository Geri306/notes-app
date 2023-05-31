package com.codecool.notes.security.auth;

import com.codecool.notes.api.controller.dto.LoginRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    public LoginRole checkAdminRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return new LoginRole(Set.of("USER", "ADMIN"));
        }
        return new LoginRole(Set.of("USER"));
    }
}
