package com.codecool.notes.logic;

import com.codecool.notes.data.Label;
import com.codecool.notes.persistence.entity.Note;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class FormatServiceTest {

    FormatService formatService = new FormatService();

    @Test
    void assignNextLabelToNote() {
        Note note = Note.builder().label(Label.GREEN).build();
        Label expected = Label.YELLOW;

        Label actual = formatService.assignNextLabelToNote(note).getLabel();

        assertEquals(expected, actual);
    }

    @Test
    void formatDate() {
        String unformatted = "2023-05-15T17:26:34.609611300";
        LocalDateTime parsed = LocalDateTime.parse(unformatted);
        Note note = Note.builder().modifiedLong(parsed).build();
        String expected = "2023/05/15 17:26";

        Note formattedNote = formatService.formatDate(note);
        String actual = formattedNote.getModified();

        assertEquals(expected, actual);
    }

    @Test
    void updateNote() {
        Note oldNote = Note.builder().id(1L).content("eat").build();
        Note newNote = Note.builder().content("code").build();
        Note expected = Note.builder().id(1L).content("code").build();

        Note actual = formatService.updateNote(oldNote, newNote);

        assertThat(expected, is(actual));
    }

}
