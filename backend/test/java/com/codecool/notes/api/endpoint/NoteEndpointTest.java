package com.codecool.notes.api.endpoint;

import com.codecool.notes.api.controller.NoteController;
import com.codecool.notes.api.exception.NoteNotFoundException;
import com.codecool.notes.data.Label;
import com.codecool.notes.persistence.entity.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class NoteEndpointTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    NoteController noteController;

    String uri = "api/v1/notes";

    @Test
    void getAll() {
        String getAllUri = uri;

        webTestClient.get()
                .uri(getAllUri)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        verify(noteController).findAll();
    }

    @Test
    void getOneFindsEntity() throws NoteNotFoundException {
        Long id = 1L;
        Note note = Note.builder().id(id).build();
        String getOneUri = uri + "/" + id;
        when(noteController.findById(id)).thenReturn(note);

        webTestClient.get()
                .uri(getOneUri)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        verify(noteController).findById(id);
    }

    @Test
    void post() {
        String createUri = uri;

        webTestClient.post()
                .uri(createUri)
                .exchange()
                .expectStatus()
                .isCreated();

        verify(noteController).addNewEmptyNote();
    }

    @Test
    void put() throws NoteNotFoundException {
        Long oldNoteId = 1L;
        String putUri = uri + "/" + oldNoteId;
        Note newNote = Note.builder().content("code").build();
        when(noteController.updateNote(newNote, oldNoteId)).thenReturn(newNote);

        webTestClient.put()
                .uri(putUri)
                .bodyValue(newNote)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        verify(noteController).updateNote(newNote, oldNoteId);
    }

    @Test
    void whenPutNextLabelOnExistingEntity_thenSuccess() throws NoteNotFoundException {
        Long id = 1L;
        String putUri = uri + "/nextlabel/" + id;
        Note note = Note.builder().id(id).label(Label.GREEN).build();
        when(noteController.assignNextLabelToNote(id)).thenReturn(note);

        webTestClient.put()
                .uri(putUri)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        verify(noteController).assignNextLabelToNote(id);
    }

    @Test
    void deleteAll() {
        String deleteAllUri = uri;

        webTestClient.delete()
                .uri(deleteAllUri)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        verify(noteController).deleteAll();
    }

    @Test
    void deleteOne() {
        Long id = 1L;
        String deleteOneUri = uri + "/" + id;

        webTestClient.delete()
                .uri(deleteOneUri)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        verify(noteController).deleteById(id);
    }
}