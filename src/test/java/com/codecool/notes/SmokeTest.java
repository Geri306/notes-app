package com.codecool.notes;

import com.codecool.notes.api.endpoint.NoteEndpoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private NoteEndpoint noteEndpoint;

    @Test
    public void contextLoads() throws Exception {
        assertThat(noteEndpoint).isNotNull();
    }
}
