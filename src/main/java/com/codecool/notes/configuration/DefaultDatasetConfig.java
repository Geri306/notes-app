package com.codecool.notes.configuration;

import com.codecool.notes.persistence.entity.Note;
import com.codecool.notes.persistence.repository.NoteRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "datasets")
@Setter
@Slf4j
public class DefaultDatasetConfig {

    @Value("${app.loadDefaultDataset:false}")
    private boolean loadDefaultDataset;
    private List<Note> notes;

    @Bean
    ApplicationRunner populateDatabase(NoteRepository noteRepository) {
        return args -> {
            if (loadDefaultDataset) {
                noteRepository.saveAll(notes);
                noteRepository.findAll().forEach(note -> log.info("Preloaded " + note));
            }
        };
    }
}
