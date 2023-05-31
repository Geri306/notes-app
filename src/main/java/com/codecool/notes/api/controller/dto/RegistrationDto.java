package com.codecool.notes.api.controller.dto;

public record RegistrationDto(String encodedAuth, boolean asAdmin) {
}
