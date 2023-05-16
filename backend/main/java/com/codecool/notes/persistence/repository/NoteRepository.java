package com.codecool.notes.persistence.repository;

import com.codecool.notes.persistence.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>, QueryRepository {

    List<Note> findByDoneTrue();

}
