package com.codecool.runner;

import com.codecool.persistence.entity.Note;
import com.codecool.persistence.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "datasets")
public class DatabasePopulator {

    private List<Note> notes;
    private final Logger log = LoggerFactory.getLogger(DatabasePopulator.class);

    @Bean
    ApplicationRunner populateDatabase(NoteRepository noteRepository) {
        return args -> {
            noteRepository.saveAll(notes);
            noteRepository.findAll().forEach(note -> log.info("Preloaded " + note));
        };
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
