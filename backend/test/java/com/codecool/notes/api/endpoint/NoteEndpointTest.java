package com.codecool.notes.api.endpoint;

import com.codecool.notes.data.Label;
import com.codecool.notes.logic.NoteService;
import com.codecool.notes.persistence.entity.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class NoteEndpointTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    NoteService noteService;

    String uri = "api/v1/notes";

    @Test
    void getAll() {
        String getAllUri = uri;

        webTestClient.get()
                .uri(getAllUri)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        verify(noteService).findAll();
    }

    @Test
    void getOneFindsEntity() {
        Long id = 1L;
        Note note = Note.builder().id(id).build();
        String getOneUri = uri + "/" + id;
        when(noteService.findById(id)).thenReturn(Optional.of(note));
        when(noteService.formatDate(note)).thenReturn(note);

        webTestClient.get()
                .uri(getOneUri)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        verify(noteService).findById(id);
    }

    @Test
    void getOneDoesNotFindEntity() {
        Long id = 1L;
        String getOneUri = uri + "/" + id;

        webTestClient.get()
                .uri(getOneUri)
                .exchange()
                .expectStatus()
                .isNotFound();

        verify(noteService).findById(id);
    }

    @Test
    void post() {
        String createUri = uri;

        webTestClient.post()
                .uri(createUri)
                .exchange()
                .expectStatus()
                .isCreated();

        verify(noteService).addNewEmptyNote();
    }

    @Test
    void put() {
        Long id = 1L;
        String putUri = uri + "/" + id;
        Note note = Note.builder().id(id).build();

        webTestClient.put()
                .uri(putUri)
                .bodyValue(note)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        verify(noteService).save(note);
    }

    // goal: communication works?
    @Test
    void whenPutNextLabelOnNonExistingEntity_thenNotFound() {
        Long id = 1L;
        String putUri = uri + "/nextlabel/" + id;

        webTestClient.put()
                .uri(putUri)
                .exchange()
                .expectStatus()
                .isNotFound();

        verify(noteService).findById(id);
    }

    @Test
    void whenPutNextLabelOnExistingEntity_thenSuccess() {
        Long id = 1L;
        String putUri = uri + "/nextlabel/" + id;
        Note note = Note.builder().id(id).label(Label.GREEN).build();
        when(noteService.findById(id)).thenReturn(Optional.of(note));
        when(noteService.assignNextLabelToNote(note)).thenReturn(note);
        when(noteService.save(note)).thenReturn(note);

        webTestClient.put()
                .uri(putUri)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        verify(noteService).findById(id);
    }

    @Test
    void deleteAll() {
        String deleteAllUri = uri;

        webTestClient.delete()
                .uri(deleteAllUri)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        verify(noteService).deleteAll();
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

        verify(noteService).deleteById(id);
    }
}