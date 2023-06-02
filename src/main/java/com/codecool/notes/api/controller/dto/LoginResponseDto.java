package com.codecool.notes.api.controller.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record LoginResponseDto(String email, Set<String> roles, String credentials) {
}
