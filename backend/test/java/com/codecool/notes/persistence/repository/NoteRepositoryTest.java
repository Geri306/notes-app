package com.codecool.notes.persistence.repository;

import com.codecool.notes.persistence.entity.Note;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest // this starts the app context but only creates the repos
@EnableConfigurationProperties(NoteRepositoryTest.class)
@ConfigurationProperties("testdatasets")
class NoteRepositoryTest {

    @Autowired
    NoteRepository noteRepository;

    List<Note> notes;

    @BeforeEach
    void setUp() {
        noteRepository.saveAll(notes);
    }

    @AfterEach
    void tearDown() {
        noteRepository.deleteAll();
    }

    @Test
    void findByDoneTrue() {
        List<Long> expected = notes
                .stream()
                .filter(Note::isDone)
                .map(Note::getId)
                .toList();

        List<Long> actual = noteRepository.findByDoneTrue()
                .stream()
                .map(Note::getId)
                .toList();

        assertIterableEquals(expected, actual);
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}