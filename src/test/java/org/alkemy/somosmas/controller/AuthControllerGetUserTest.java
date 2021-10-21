package org.alkemy.somosmas.controller;

import org.alkemy.somosmas.SomosmasApplicationTests;
import org.alkemy.somosmas.model.User;
import org.alkemy.somosmas.security.JWTResponse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthControllerGetUserTest extends SomosmasApplicationTests {

    @Test
    void getUser_withAuthenticatedUser_mustReturnUser() throws IOException {

        var requestLogin = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/login/request/postLogin_withValidLoginRequest_mustReturnToken.json")));
        var headersLogin = new HttpHeaders();

        headersLogin.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));

        var httpEntityLogin = new HttpEntity(requestLogin, headersLogin);

        // Get authentication token
        var responseLogin = restTemplate.exchange("/auth/login", HttpMethod.POST, httpEntityLogin, JWTResponse.class);

        assertThat(responseLogin.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseLogin.getBody()).isNotNull();

        var token = responseLogin.getBody().getToken();

        var headersGetUser = new HttpHeaders();
        headersGetUser.setBearerAuth(token);

        var httpEntityGetUser = new HttpEntity(headersGetUser);

        // Get authenticated user
        var responseGetUser = restTemplate.exchange("/auth/me", HttpMethod.GET, httpEntityGetUser, User.class);

        var loggedInUser = responseGetUser.getBody();

        assertThat(responseGetUser.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(loggedInUser).isNotNull();
        assertThat(loggedInUser.getFirstName()).isEqualTo("noah");
        assertThat(loggedInUser.getLastName()).isEqualTo("smith");
        assertThat(loggedInUser.getEmail()).isEqualTo("noah_smith@gmail.com");

    }

    @Test
    void getUser_withAnonymousUser_statusCodeMustBeForbidden() {

        var response = restTemplate.exchange("/auth/me", HttpMethod.GET, null, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);

    }

}
