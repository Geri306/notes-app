package com.codecool.notes.api.controller.dto;

import com.codecool.notes.data.Label;
import com.codecool.notes.persistence.entity.Note;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class Mapper {

    public NoteDTO toDto(Note note) {
        Long id = note.getId();
        Boolean done = note.isDone();
        String content = note.getContent();
        Label label = note.getLabel();
        LocalDateTime modifiedLong = note.getModifiedLong();
        String  modified = note.getModified();

        return new NoteDTO(id, done, content, label, modifiedLong, modified);
    }

    public Note toNote(NoteDTO noteDTO) {
        return new Note(noteDTO.getId(),
                noteDTO.isDone(),
                noteDTO.getContent(),
                noteDTO.getLabel(),
                noteDTO.getModifiedLong(),
                noteDTO.getModified());
    }
}
