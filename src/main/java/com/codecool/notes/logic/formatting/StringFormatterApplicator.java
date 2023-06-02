package com.codecool.notes.logic.formatting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StringFormatterApplicator {

    private final List<Formatter> formatters;

    public String applyFormatters(String input) {
        return formatters.stream()
                .reduce(input, (result, formatter) -> formatter.format(result), (a, b) -> b);
    }
}
