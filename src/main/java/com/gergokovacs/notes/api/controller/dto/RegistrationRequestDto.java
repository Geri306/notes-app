package com.gergokovacs.notes.api.controller.dto;

public record RegistrationRequestDto(String encodedAuth, boolean asAdmin) {
}
