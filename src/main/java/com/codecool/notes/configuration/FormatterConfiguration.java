package com.codecool.notes.configuration;

import com.codecool.notes.logic.formatting.Decoder;
import com.codecool.notes.logic.formatting.Extractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class FormatterConfiguration {

    @Bean
    @Order(1)
    Extractor extractor() {
        return new Extractor();
    }

    @Bean
    @Order(2)
    Decoder decoder() {
        return new Decoder();
    }
}
