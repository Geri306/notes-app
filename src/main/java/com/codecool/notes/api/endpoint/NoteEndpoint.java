package com.codecool.notes.api.endpoint;

import com.codecool.notes.api.controller.NoteController;
import com.codecool.notes.api.exception.note.NoteNotFoundException;
import com.codecool.notes.persistence.entity.Note;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notes")
@RequiredArgsConstructor
@Slf4j
public class NoteEndpoint {

    private final NoteController noteController;

    @GetMapping
    public List<Note> getAll() {
        return noteController.findAll();
    }

    @GetMapping("{id}")
    Note getOne(@PathVariable Long id) throws NoteNotFoundException {
        return noteController.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('USER')")
    Note post() {
        log.info("Request to post new note");
        return noteController.addNewEmptyNote();
    }

    @PutMapping("{oldNoteId}")
    @PreAuthorize("hasAuthority('USER')")
    Note put(@Valid @RequestBody Note newNote, @PathVariable Long oldNoteId) throws NoteNotFoundException {
        log.info("Request to update note with id: " + oldNoteId);
        return noteController.updateNote(newNote, oldNoteId);
    }

    @PutMapping("nextlabel/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    Note putNextLabel(@PathVariable Long id) throws NoteNotFoundException {
        log.info("Request to change label on note with id: " + id);
        return noteController.assignNextLabelToNote(id);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    void deleteAll() {
        log.info("Request to delete all notes");
        noteController.deleteAll();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    void deleteOne(@PathVariable Long id) {
        log.info("Request to delete note with id: " + id);
        noteController.deleteById(id);
    }
}
