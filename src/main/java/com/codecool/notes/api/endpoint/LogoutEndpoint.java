package com.codecool.notes.api.endpoint;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logout")
public class LogoutEndpoint {

    @GetMapping
    public void logout() {
        // Clear authentication from SecurityContextHolder
        SecurityContextHolder.clearContext();
    }
}
