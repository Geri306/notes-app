package com.codecool.notes.api.endpoint;

import com.codecool.notes.api.controller.NoteController;
import com.codecool.notes.api.controller.dto.GetAllDto;
import com.codecool.notes.api.controller.dto.LoginResponseDto;
import com.codecool.notes.api.exception.note.NoteNotFoundException;
import com.codecool.notes.persistence.entity.Note;
import com.codecool.notes.security.auth.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("api/v1/notes")
@RequiredArgsConstructor
@Slf4j
public class NoteEndpoint {

    private final NoteController noteController;
    private final AuthenticationService authenticationService;
//    private final AuthenticationManager authenticationManager;

    @GetMapping
    public List<Note> getAll() {
//        System.out.println("authorizationHeader = " + authorizationHeader);
//        String encodedCredentials = authorizationHeader.substring("Basic ".length());
//        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
//        String decodedCredentials = new String(decodedBytes);
//        String[] credentials = decodedCredentials.split(":");
//        String email = credentials[0];
//        String password = credentials[1];
//        try {
////            Authentication authentication = authenticationManager.authenticate(
////                    new UsernamePasswordAuthenticationToken(email, password)
////            );
////            SecurityContextHolder.getContext().setAuthentication(authentication);
////            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
////            String encodedAuth = Base64.getEncoder().encodeToString((userDetails.getUsername() + ":" + userDetails.getPassword()).getBytes());
////            GetAllDto body = new GetAllDto(
////                    noteController.findAll(),
////                    userDetails.getUsername(),
////                    authenticationService.getRoles(),
////                    encodedAuth
////            );
//            return ResponseEntity.ok().build();/*.body(body);*/
//        } catch (Exception e) {
//            System.err.println(e.getMessage() + ", " + e.getCause());
//            return ResponseEntity.internalServerError().build();
//        }
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
