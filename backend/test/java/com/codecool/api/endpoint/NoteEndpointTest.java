package com.codecool.api.endpoint;

import com.codecool.data.Label;
import com.codecool.logic.NoteService;
import com.codecool.persistence.entity.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class NoteEndpointTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    NoteService noteService;

    @MockBean
    Note mockNote;

    String uri = "api/v1/notes";

    @Test
    void getAll() {
        String getAllUri = uri + "/get/all";

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
        String getOneUri = uri + "/get/" + id;
        when(noteService.findById(id)).thenReturn(Optional.of(note));

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
        String getOneUri = uri + "/get/" + id;

        webTestClient.get()
                .uri(getOneUri)
                .exchange()
                .expectStatus()
                .isNotFound();

        verify(noteService).findById(id);
    }

    @Test
    void post() {
        String createUri = uri + "/new";

        webTestClient.post()
                .uri(createUri)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        verify(noteService).addNewEmptyNote();
    }

    @Test
    void put() {
        Long id = 1L;
        String putUri = uri + "/put/" + id;
        Note note = Note.builder().id(id).build();

        webTestClient.put()
                .uri(putUri)
                .bodyValue(note)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        verify(noteService).save(note);
    }

    @Test
    void putNextLabelOnExistingEntity() {
        Long id = 1L;
        String putUri = uri + "/put/nextlabel/" + id;

        webTestClient.put()
                .uri(putUri)
                .exchange()
                .expectStatus()
                .isNotFound();

        verify(noteService).findById(id);
    }

    @Test
    void deleteAll() {
        String deleteAllUri = uri + "/delete/all";

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
        String deleteOneUri = uri + "/delete/one/" + id;

        webTestClient.delete()
                .uri(deleteOneUri)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        verify(noteService).deleteById(id);
    }
}