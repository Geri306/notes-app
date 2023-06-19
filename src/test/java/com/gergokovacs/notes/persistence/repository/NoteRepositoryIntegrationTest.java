package com.gergokovacs.notes.persistence.repository;

import com.gergokovacs.notes.persistence.entity.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NoteRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void whenFindByContent_thenReturnNote() {
        Note note = Note.builder().content("eat").build();
        entityManager.persistAndFlush(note);

        Optional<Note> found = noteRepository.findByContent(note.getContent());
        assertThat(found.get().getContent()).isEqualTo(note.getContent());
    }

    @Test
    public void whenInvalidContent_thenReturnEmptyOptional() {
        Optional<Note> found = noteRepository.findByContent("doesNotExist");
        assertThat(found).isEmpty();
    }

    @Test
    public void whenFindById_thenReturnNote() {
        Note note = Note.builder().content("test").build();
        entityManager.persistAndFlush(note);

        Note fromDb = noteRepository.findById(note.getId()).orElse(null);
        assertThat(fromDb.getContent()).isEqualTo(note.getContent());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Note fromDb = noteRepository.findById(-11L).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfNotes_whenFindAll_thenReturnAllNotes() {
        Note note1 = Note.builder().content("eat").build();
        Note note2 = Note.builder().content("sleep").build();
        Note note3 = Note.builder().content("code").build();

        entityManager.persist(note1);
        entityManager.persist(note2);
        entityManager.persist(note3);
        entityManager.flush();

        List<Note> allNotes = noteRepository.findAll();

        assertThat(allNotes)
                .hasSize(3)
                .extracting(Note::getContent)
                .containsOnly(note1.getContent(), note2.getContent(), note3.getContent());
    }
}

