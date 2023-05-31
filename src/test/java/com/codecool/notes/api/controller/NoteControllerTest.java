package com.codecool.notes.api.controller;

import com.codecool.notes.api.exception.note.NoteNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class NoteControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    NoteController noteController;


    String uri = "api/v1/notes";

    @Test
    void getOneDoesNotFindEntity() throws NoteNotFoundException {
        Long id = -11L;
        String getOneUri = uri + "/" + id;
        when(noteController.findById(id)).thenThrow(NoteNotFoundException.class);

        webTestClient.get()
                .uri(getOneUri)
                .exchange()
                .expectStatus()
                .isNotFound();

        verify(noteController).findById(id);
    }


    // goal: communication works?
    @Test
    void whenPutNextLabelOnNonExistingEntity_thenNotFound() throws NoteNotFoundException {
        Long id = 1L;
        String putUri = uri + "/nextlabel/" + id;
        when(noteController.assignNextLabelToNote(id)).thenThrow(NoteNotFoundException.class);

        webTestClient.put()
                .uri(putUri)
                .exchange()
                .expectStatus()
                .isNotFound();

        verify(noteController).assignNextLabelToNote(id);
    }
}