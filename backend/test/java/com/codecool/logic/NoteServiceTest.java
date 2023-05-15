package com.codecool.logic;

import com.codecool.data.Label;
import com.codecool.persistence.entity.Note;
import com.codecool.persistence.repository.NoteRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class NoteServiceTest {

    NoteService noteService = new NoteService(mock(NoteRepository.class));

    @Test
    void getNoteWithNextLabel() {
        Note note = Note.builder().label(Label.GREEN).build();
        Label expected = Label.YELLOW;

        Label actual = noteService.getNoteWithNextLabel(note).getLabel();

        assertEquals(expected, actual);
    }

    @Test
    void formatDate() {
        String unformatted = "2023-05-15T17:26:34.609611300";
        LocalDateTime parsed = LocalDateTime.parse(unformatted);
        Note note = Note.builder().modified_L(parsed).build();
        String expected = "2023/05/15 17:26";

        Note formattedNote = noteService.formatDate(note);
        String actual = formattedNote.getModified();

        assertEquals(expected, actual);
    }
}