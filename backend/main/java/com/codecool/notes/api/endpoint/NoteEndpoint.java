package com.codecool.notes.api.endpoint;

import com.codecool.notes.api.exception.NoteNotFoundException;
import com.codecool.notes.logic.NoteService;
import com.codecool.notes.persistence.entity.Note;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notes")
@RequiredArgsConstructor
@Slf4j
public class NoteEndpoint {

    private final NoteService noteService;

    @GetMapping
    List<Note> getAll() {
        return noteService.findAll()
                .stream()
                .map(noteService::formatDate)
                .toList();
    }

    @GetMapping("{id}")
    Note getOne(@PathVariable Long id) {
        return noteService.findById(id)
                .orElseThrow(NoteNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Note save() {
        log.info("Request to post new note");
        return noteService.addNewEmptyNote();
    }

    @PutMapping("{id}")
    Note update(@Valid @RequestBody Note newNote, @PathVariable Long id) {
        log.info("Request to update note with id: " + id);
        return noteService.findById(id)
                .map(oldNote -> {
                    Note updatedNote = noteService.updateNote(oldNote, newNote);
                    return noteService.save(updatedNote);
                })
                .orElseGet(() -> {
                    newNote.setId(id);
                    return noteService.save(newNote);
                });
    }

    @PutMapping("nextlabel/{id}")
    Note putNextLabel(@PathVariable Long id) {
        log.info("Request to change label on note with id: " + id);
        return noteService.findById(id)
                .map(note -> {
                    Note updatedNote = noteService.assignNextLabelToNote(note);
                    return noteService.save(updatedNote);
                })
                .orElseThrow(NoteNotFoundException::new);
    }

    @DeleteMapping
    public void deleteAll() {
        log.info("Request to delete all notes");
        noteService.deleteAll();
        noteService.resetSequence();
    }

    @DeleteMapping("{id}")
    void deleteOne(@PathVariable Long id) {
        log.info("Request to delete note with id: " + id);
        noteService.deleteById(id);
    }
}
