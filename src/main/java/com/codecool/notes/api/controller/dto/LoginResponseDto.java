package com.codecool.notes.api.controller.dto;

import java.util.Set;

public record LoginResponseDto(String email, Set<String> roles, String credentials) {
}
