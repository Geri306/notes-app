package com.codecool.notes.logic;

import com.codecool.notes.data.Label;
import com.codecool.notes.persistence.entity.Note;
import com.codecool.notes.persistence.repository.NoteRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NoteServiceTest {

    NoteRepository noteRepository = mock(NoteRepository.class);
    NoteService noteService = new NoteService(noteRepository);

    @Test
    void findAll() {
        noteService.findAll();

        verify(noteRepository).findAll();
    }

    @Test
    void findById() {
        Long id = 1L;
        noteService.findById(id);

        verify(noteRepository).findById(id);
    }

    @Test
    void save() {
        Note note = Note.builder().build();
        noteService.save(note);

        verify(noteRepository).save(note);
    }

    @Test
    void addNewEmptyNote() {
        Note note = new Note();
        noteService.addNewEmptyNote();

        verify(noteRepository).save(note);
    }

    @Test
    void deleteAll() {
        noteService.deleteAll();

        verify(noteRepository).deleteAll();
    }

    @Test
    void deleteById() {
        Long id = 1L;
        noteService.deleteById(id);

        verify(noteRepository).deleteById(id);
    }

    @Test
    void resetSequence() {
        noteService.resetSequence();

        verify(noteRepository).resetSequence();
    }

    @Test
    void updateNote() {
        Long oldId = 1L;
        Note note1 = Note.builder().id(oldId).build();
        Long newId = 2L;
        Note note2 = Note.builder().id(newId).build();

        Long actual = noteService.updateNote(note1, note2).getId();

        assertEquals(oldId, actual);
    }


    @Test
    void assignNextLabelToNote() {
        Note note = Note.builder().label(Label.GREEN).build();
        Label expected = Label.YELLOW;

        Label actual = noteService.assignNextLabelToNote(note).getLabel();

        assertEquals(expected, actual);
    }

    @Test
    void formatDate() {
        String unformatted = "2023-05-15T17:26:34.609611300";
        LocalDateTime parsed = LocalDateTime.parse(unformatted);
        Note note = Note.builder().modifiedLong(parsed).build();
        String expected = "2023/05/15 17:26";

        Note formattedNote = noteService.formatDate(note);
        String actual = formattedNote.getModified();

        assertEquals(expected, actual);
    }
}