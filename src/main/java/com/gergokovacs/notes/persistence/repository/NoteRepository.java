package com.gergokovacs.notes.persistence.repository;

import com.gergokovacs.notes.persistence.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>, QueryRepository {

    List<Note> findByDoneTrue();

    Optional<Note> findByContent(String content);

}
