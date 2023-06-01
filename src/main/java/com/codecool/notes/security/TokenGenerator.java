package com.codecool.notes.security;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator {

    public TokenGenerator() {
    }

    public String generateSessionToken(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[length];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
}
