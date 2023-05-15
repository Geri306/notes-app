package com.codecool.persistence.repository;

import com.codecool.persistence.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>, QueryRepository {

}
