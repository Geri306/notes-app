package com.gergokovacs.notes.logic.formatting;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Splitter {
    public Map<String, String> split(String decodedString) {
        List<String> credentials = List.of(decodedString.split(":"));
        Map<String, String> credentialsMap = new HashMap<>();
        credentialsMap.put("email", credentials.get(0));
        credentialsMap.put("password", credentials.get(1));
        return credentialsMap;
    }
}
