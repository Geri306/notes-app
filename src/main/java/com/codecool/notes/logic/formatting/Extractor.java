package com.codecool.notes.logic.formatting;

public class Extractor implements Formatter {
    @Override
    public String format(String authHeader) {
        return authHeader.substring("Basic ".length());
    }
}
