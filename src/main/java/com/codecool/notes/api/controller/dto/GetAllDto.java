package com.codecool.notes.api.controller.dto;

import com.codecool.notes.persistence.entity.Note;

import java.util.List;
import java.util.Set;

public record GetAllDto(List<Note> notes, String email, Set<String> roles, String credentials) {
}
