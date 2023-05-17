package com.codecool.notes.api.endpoint;

import com.codecool.notes.api.controller.NoteController;
import com.codecool.notes.api.controller.dto.NoteDTO;
import com.codecool.notes.logic.NoteService;
import com.codecool.notes.persistence.entity.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class WebLayerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NoteService noteService;

    @MockBean
    private NoteController noteController;

    @Test
    public void givenNotes_whenGetNotes_thenReturnJsonArray() throws Exception {

        Note note1 = Note.builder().id(1L).content("eat").build();
        Note note2 = Note.builder().id(1L).content("sleep").build();
        Note note3 = Note.builder().id(1L).content("code").build();

        List<Note> allNotes = Arrays.asList(note1, note2, note3);

        given(noteController.findAll()).willReturn(allNotes);

        mvc.perform(get("/api/v1/notes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].content", is(note1.getContent())))
                .andExpect(jsonPath("$[1].content", is(note2.getContent())))
                .andExpect(jsonPath("$[2].content", is(note3.getContent())));

        verify(noteController, times(1)).findAll();
        reset(noteService);
        reset(noteController);
    }

    @Test
    public void whenPostNote_thenReturnJson() throws Exception {

        Note note = Note.builder().id(1L).build();

        given(noteController.addNewEmptyNote()).willReturn(note);

        mvc.perform(post("/api/v1/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(note)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)));

        verify(noteController, times(1)).addNewEmptyNote();
        reset(noteService);
        reset(noteController);
    }
}
