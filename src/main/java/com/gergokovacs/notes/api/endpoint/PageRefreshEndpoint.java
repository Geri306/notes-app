package com.gergokovacs.notes.api.endpoint;

import com.gergokovacs.notes.api.controller.AuthenticationController;
import com.gergokovacs.notes.api.controller.dto.UserDetailsRefreshDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("latest_userdetails")
@RequiredArgsConstructor
public class PageRefreshEndpoint {

    private final AuthenticationController authenticationController;

    @GetMapping
    UserDetailsRefreshDto sendLatestUserDetails(Authentication authentication) {
        return authenticationController.sendLatestUserDetails(authentication);
    }
}
