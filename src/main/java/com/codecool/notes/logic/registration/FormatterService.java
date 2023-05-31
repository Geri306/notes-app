package com.codecool.notes.logic.registration;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FormatterService {
    public String decode(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }

    public Map<String, String> split(String decodedString) {
        List<String> credentials = Arrays.stream(decodedString.split(":")).toList();
        Map<String, String> credentialsMap = new HashMap<>();
        credentialsMap.put("email", credentials.get(0));
        credentialsMap.put("password", credentials.get(1));
        return credentialsMap;
    }
}
