package com.codecool.api.endpoint;

import com.codecool.api.exception.NoteNotFoundException;
import com.codecool.logic.NoteService;
import com.codecool.persistence.entity.Note;
import com.codecool.persistence.repository.NoteRepository;
import com.codecool.persistence.repository.QueryRepository;
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
    private final NoteRepository noteRepository;
    private final NoteService noteService;

    @PostMapping("new")
    ResponseEntity<Note> create() throws URISyntaxException {
        log.info("Request to create new note");
        Note result = noteRepository.save(new Note());
        return ResponseEntity
                .created(new URI("api/v1/notes/get/" + result.getId()))
                .body(result);
    }

    // TEST START

/*    @RequestMapping("/handle")
    @PostMapping
    public ResponseEntity<String> handle() throws URISyntaxException {
//        URI location = new URI("api/mydocs");
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.setLocation(location);
//        responseHeaders.set("MyResponseHeader", "MyValue");
//        return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
        URI location = new URI("api/mydocs");
        return ResponseEntity.created(location).header("MyResponseHeader", "MyValue").body("Hello World");
    }*/

    // TEST END

    @GetMapping("get/{id}")
    ResponseEntity<?> getOneById(@PathVariable Long id) throws NoteNotFoundException {
        Optional<Note> result = noteRepository.findById(id);
        return result
                .map(note -> {
                    Note formattedNote = noteService.formatDate(note);
                    return ResponseEntity.ok().body(formattedNote);
                })
                .orElseThrow(() -> new NoteNotFoundException(
                        "cannot find note with id: " + id));
    }

    @GetMapping("get/all")
    List<Note> readAll() {
        noteRepository.findAll().forEach(noteService::formatDate);
        return noteRepository.findAll();
    }

    @PutMapping("put/{id}")
    ResponseEntity<Note> replaceNote(@Valid @RequestBody Note newNote, @PathVariable long id) {
        log.info("Request to update note with id: " + id);
        return noteRepository.findById(id)
                .map(oldNote -> {
                    Note updatedNote = noteService.getUpdatedNote(oldNote, newNote);
                    Note result = noteRepository.save(updatedNote);
                    return ResponseEntity.ok().body(result);
                })
                .orElseGet(() -> {
                    newNote.setId(id);
                    Note result = noteRepository.save(newNote);
                    return ResponseEntity.ok().body(result);
                });
    }

    @PutMapping("put/nextlabel/{id}")
    ResponseEntity<Note> replaceNote(@PathVariable long id) {
        log.info("Request to change label on note with id: " + id);
        return noteRepository.findById(id)
                .map(note -> {
                    Note updatedNote = noteService.getNoteWithNextLabel(note);
                    noteRepository.save(updatedNote);
                    return ResponseEntity.ok().body(updatedNote);
                })
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Label change request for non-existing note with id: " + id
                        ));

    }

    @DeleteMapping("delete/one/{id}")
    void deleteOneById(@PathVariable long id) {
        log.info("Request to delete note with id: " + id);
        noteRepository.deleteById(id);
        ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/all")
    public ResponseEntity<?> deleteAll() {
        log.info("Request to delete all notes");
        noteRepository.deleteAll();
        noteRepository.resetSequence();
        return ResponseEntity.ok().build();
    }
}
