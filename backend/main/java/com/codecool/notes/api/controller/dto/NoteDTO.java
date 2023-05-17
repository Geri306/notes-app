package com.codecool.notes.api.controller.dto;

import com.codecool.notes.data.Label;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.codecool.notes.persistence.entity.Note} entity
 */
@Data
@AllArgsConstructor
public class NoteDTO implements Serializable {

    private Long id;
    private boolean done;
    private String content;
    private Label label;
    private LocalDateTime modifiedLong;
    private String modified;
}