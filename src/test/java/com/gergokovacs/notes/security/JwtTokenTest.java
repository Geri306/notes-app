package com.gergokovacs.notes.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gergokovacs.notes.api.controller.AuthenticationController;
import com.gergokovacs.notes.logic.registration.RegistrationService;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class JwtTokenTest {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    MockMvc mvc;

    String loginUrl = "http://localhost:9000/login";

    @Test
    @DisplayName("generate JWT token on authentication")
    void givenAdminLogsInAndGetsToken_ThenProtectedRouteWithTokenIsAccessible() throws Exception {

        MvcResult result = mvc
                .perform(post(loginUrl).with(httpBasic("admin@gmail.com", "Admin!111")))
                .andExpect(status().isOk())
                .andReturn();

        String jsonString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> response = mapper.readValue(jsonString, Map.class);
        String token = (String) response.get("token");

        mvc.perform(get("/")
                        .header("Authorization", "Bearer " + token))
                .andExpect(content().string("Hello admin@gmail.com"));
    }

    @Test
    void givenTokenIsTamperedWith_WhenUserLoggedIn_ThenUnauthenticated() throws Exception {
        MvcResult result = mvc.perform(post(loginUrl)
                        .with(httpBasic("admin@gmail.com", "Admin!111")))
                .andExpect(status().isOk())
                .andReturn();

        String jsonString = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(jsonString);
        String invalidToken = jsonObject.getString("token").replace("a", "b");

        mvc.perform(delete("http://localhost:9000/api/v1/notes")
                        .header("Authorization", "Bearer " + invalidToken))
                .andExpect(status().isUnauthorized()); // 401
    }
}
