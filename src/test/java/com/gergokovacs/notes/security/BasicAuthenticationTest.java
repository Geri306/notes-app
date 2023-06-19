package com.gergokovacs.notes.security;

import com.gergokovacs.notes.api.controller.dto.RegistrationRequestDto;
import com.gergokovacs.notes.api.endpoint.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.json.JSONObject;

import java.util.Base64;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest//(webEnvironment = RANDOM_PORT) //todo: is random port needed in this scenario?
public class BasicAuthenticationTest {

    @Autowired
    MockMvc mvc;

    String landingUrl = "http://localhost:9000/api/v1/notes";
    String loginUrl = "http://localhost:9000/login";

    @Test
    void accessUnsecuredRoute_WithoutCredentials_ThenOk() throws Exception {
        mvc.perform(get(landingUrl))
                .andExpect(status().isOk());
    }

    @Test
    void accessProtectedRoute_WithoutCredentials_ThenUnauthenticated() throws Exception {
        mvc.perform(post(landingUrl))
                .andExpect(unauthenticated());
    }

    @Test
    @WithAnonymousUser
    void accessProtectedRoute_AsAnonymousUser_ThenUnauthenticated() throws Exception {
        mvc.perform(post(landingUrl))
                .andExpect(unauthenticated());
    }

    @Test
    void accessProtectedRoute_WithValidUser_ThenAuthenticated() throws Exception {
        mvc.perform(post(landingUrl).with(httpBasic("user@gmail.com", "User!111")))
                .andExpect(authenticated().withUsername("user@gmail.com"));
    }

    @Test
    void accessProtectedRoute_WithInvalidCredentials_ThenUnauthenticated() throws Exception {
        mvc.perform(post(landingUrl).with(httpBasic("user", "invalid")))
                .andExpect(unauthenticated());
    }

    @Test
    void accessUnprotectedRoute_WithoutCredentials_ThenOk() throws Exception {
        mvc.perform(get(landingUrl))
                .andExpect(status().isOk());
    }

    @Test
    void givenLoggedInAsAdmin_WhenPostNewNoteOnProtectedRoute_ThenCreated() throws Exception {
        MvcResult result = mvc.perform(post(loginUrl).with(httpBasic("admin@gmail.com", "Admin!111")))
                .andExpect(status().isOk())
                .andReturn();

        String jsonString = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(jsonString);
        String token = jsonObject.getString("token");

        mvc.perform(post(landingUrl).header("Authorization", "Bearer " + token))
                .andExpect(status().isCreated());
    }

    @Test
    void givenLoggedInAsUser_WhenDeleteAll_ThenForbidden() throws Exception {
        MvcResult result = mvc.perform(post(loginUrl).with(httpBasic("user@gmail.com", "User!111")))
                .andExpect(status().isOk())
                .andReturn();

        String jsonString = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(jsonString);
        String token = jsonObject.getString("token");

        mvc.perform(delete(landingUrl).header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());
    }

    @Test
    void tryDeleteAll_WithoutAuthentication_ThenForbidden() throws Exception {
        mvc.perform(delete(landingUrl))
                .andExpect(unauthenticated());
    }

    @Test
    @WithAnonymousUser
    void tryDeleteAll_AsAnonymousUser_ThenForbidden() throws Exception {
        mvc.perform(delete(landingUrl))
                .andExpect(unauthenticated());
    }

    @Test
    void givenLoggedInAsAdmin_WhenDeleteAll_ThenOk() throws Exception {
        MvcResult result = mvc.perform(post(loginUrl).with(httpBasic("admin@gmail.com", "Admin!111")))
                .andExpect(status().isOk())
                .andReturn();

        String jsonString = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(jsonString);
        String token = jsonObject.getString("token");

        mvc.perform(delete(landingUrl).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void whenAdminLogin_withValidCredentials_ThenOkAndAuthenticatedAsAdmin() throws Exception {
        Set<String> authorities = Set.of("ADMIN", "USER");
        Collection<? extends GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));

        mvc.perform(post(loginUrl).with(httpBasic("admin@gmail.com", "Admin!111")))
                .andExpect(status().isOk())
                .andExpect(authenticated().withUsername("admin@gmail.com"))
                .andExpect(authenticated().withAuthorities(grantedAuthorities));
    }

    static Stream<Arguments> credentialsProvider() {
        return Stream.of(
                Arguments.of(Set.of("ADMIN", "USER"), "admin@gmail.com", "Admin!111"),
                Arguments.of(Set.of("USER"), "user@gmail.com", "User!111")
        );
    }

    @ParameterizedTest
    @MethodSource("credentialsProvider")
    void whenLogin_withValidCredentials_ThenOkAndAuthenticatedAccordingly(
            Set<String> authorities,
            String username,
            String password
    ) throws Exception {
        Collection<? extends GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));

        mvc.perform(post(loginUrl).with(httpBasic(username, password)))
                .andExpect(status().isOk())
                .andExpect(authenticated().withUsername(username))
                .andExpect(authenticated().withAuthorities(grantedAuthorities));
    }
}