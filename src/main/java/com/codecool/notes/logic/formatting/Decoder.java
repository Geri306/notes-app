package com.codecool.notes.logic.formatting;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Decoder {
    public String format(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }
}
