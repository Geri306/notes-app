package com.codecool.persistence.repository;

import org.springframework.data.jpa.repository.Query;

public interface QueryRepository {
    // 0 is out of bound, that's why we need to set the optional 'is_called' ...
    // ... flag to false so that after resetting the first record gets the id 1
    @Query("select setval('note_id_sequence', 1, false)")
    void resetSequence();
}
