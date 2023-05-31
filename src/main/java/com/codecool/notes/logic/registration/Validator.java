package com.codecool.notes.logic.registration;

import java.util.List;

public interface Validator {

    void validate(String encodedCredentials) throws Exception;
}
