package com.codecool.api.endpoint;

import com.codecool.api.exception.NoteNotFoundException;
import com.codecool.logic.NoteService;
import com.codecool.persistence.entity.Note;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/notes/")
@AllArgsConstructor
@Slf4j
public class NoteEndpoint {

    //TODO migrate all calls to noteRepository to noteService
    private final NoteService noteService;

    @GetMapping("get/all")
    List<Note> getAll() {
        List<Note> notes = noteService.findAll();
        notes.forEach(noteService::formatDate);
        return notes;
    }

    @GetMapping("get/{id}")
    ResponseEntity<?> getOne(@PathVariable Long id) {
        Optional<Note> result = noteService.findById(id);
        return result
                .map(note -> {
                    Note formattedNote = noteService.formatDate(note);
                    return ResponseEntity.ok().body(formattedNote);
                })
                .orElseThrow(() -> new NoteNotFoundException(
                        "cannot find note with id: " + id));
    }

    @PostMapping("new")
    ResponseEntity<Note> post() throws URISyntaxException {
        log.info("Request to post new note");
        Note result = noteService.addNewEmptyNote();
        return ResponseEntity.ok().body(result);
        //TODO how to test this? because 'result' will be null
//        return ResponseEntity
//                .created(new URI("api/v1/notes/get/" + result.getId()))
//                .body(result);
    }

    @PutMapping("put/{id}")
    ResponseEntity<Note> put(@Valid @RequestBody Note newNote, @PathVariable Long id) {
        log.info("Request to update note with id: " + id);
        return noteService.findById(id)
                .map(oldNote -> {
                    Note updatedNote = noteService.updateNote(oldNote, newNote);
                    Note result = noteService.save(updatedNote);
                    return ResponseEntity.ok().body(result);
                })
                .orElseGet(() -> {
                    newNote.setId(id);
                    Note result = noteService.save(newNote);
                    return ResponseEntity.ok().body(result);
                });
    }

    @PutMapping("put/nextlabel/{id}")
    ResponseEntity<Note> putNextLabel(@PathVariable Long id) {
        log.info("Request to change label on note with id: " + id);
        return noteService.findById(id)
                .map(note -> {
                    Note updatedNote = noteService.getNoteWithNextLabel(note);
                    noteService.save(updatedNote);
                    return ResponseEntity.ok().body(updatedNote);
                })
                .orElseThrow(
                        () -> new NoteNotFoundException(
                                "Label change request for non-existing note with id: " + id
                        ));

    }

    @DeleteMapping("delete/all")
    public ResponseEntity<?> deleteAll() {
        log.info("Request to delete all notes");
        noteService.deleteAll();
        noteService.resetSequence();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/one/{id}")
    void deleteOne(@PathVariable Long id) {
        log.info("Request to delete note with id: " + id);
        noteService.deleteById(id);
        ResponseEntity.ok().build();
    }
}
