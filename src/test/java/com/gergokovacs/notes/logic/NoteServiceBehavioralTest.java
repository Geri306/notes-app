package com.gergokovacs.notes.logic;

import com.gergokovacs.notes.api.exception.note.NoteNotFoundException;
import com.gergokovacs.notes.logic.note.NoteMaintainer;
import com.gergokovacs.notes.logic.note.NoteService;
import com.gergokovacs.notes.persistence.entity.Note;
import com.gergokovacs.notes.persistence.repository.NoteRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NoteServiceBehavioralTest {

    NoteRepository noteRepository = mock(NoteRepository.class);
    NoteMaintainer noteMaintainer = mock(NoteMaintainer.class);
    NoteService noteService = new NoteService(noteRepository, noteMaintainer);

    @Test
    void findAll() {
        noteService.findAll();

        verify(noteRepository).findAll();
    }

    @Test
    void findById() throws NoteNotFoundException {
        Long id = 1L;
        when(noteRepository.findById(id)).thenReturn(Optional.of(Note.builder().id(1L).build()));

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



}