package com.codecool.notes.logic.login;

import com.codecool.notes.api.controller.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DtoService {
    public LoginResponseDto instantiate(String email, Set<String> roles) {
        return LoginResponseDto.builder()
                .email(email)
                .roles(roles)
                .build();
    }
}
