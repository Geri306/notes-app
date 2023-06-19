package com.gergokovacs.notes.security;

import com.gergokovacs.notes.api.controller.dto.RegistrationRequestDto;
import com.gergokovacs.notes.api.endpoint.JsonUtil;
import com.gergokovacs.notes.persistence.entity.User;
import com.gergokovacs.notes.persistence.entity.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    UserRepository userRepository;

    String registerUrl = "http://localhost:9000/register";

    static Stream<Arguments> credentialsProvider() {
        return Stream.of(
                Arguments.of("newuser@email.com", "User!111", false, Set.of("USER")),
                Arguments.of("newadmin@email.com", "Admin!111", true, Set.of("USER", "ADMIN"))
        );
    }

    @ParameterizedTest
    @MethodSource("credentialsProvider")
    void whenRegisterOnUnprotectedRoute_WithValidCredentials_ThenOkAndUserFoundInDb(
            String email,
            String password,
            boolean asAdmin,
            Set<String> expectedAuthorities
    ) throws Exception {
        String originalInput = format("%s:%s", email, password);
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        RegistrationRequestDto registrationRequestDto = new RegistrationRequestDto(encodedString, asAdmin);
        byte[] json = JsonUtil.toJson(registrationRequestDto);

        mvc.perform(post(registerUrl).contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        Optional<User> oUser = userRepository.findByEmail(email);
        assertTrue(oUser.isPresent());

        Set<String> actualAuthorities = oUser.get().getAuthorities();
        boolean sizeEqual = expectedAuthorities.size() == actualAuthorities.size();
        boolean fullIntersection = expectedAuthorities.containsAll(actualAuthorities);
        assertTrue(sizeEqual && fullIntersection);
    }
}
