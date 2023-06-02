package com.codecool.notes.logic.formatting;

import java.util.Base64;

public class Decoder implements Formatter {
    @Override
    public String format(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }
}
