package com.gergokovacs.notes.api.endpoint;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestEndpoint {
    @GetMapping
    String welcome(Authentication authentication) {
        String user = authentication.getName();
        return "Hello " + user;
    }
}
