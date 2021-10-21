package org.alkemy.somosmas.controller;

import org.alkemy.somosmas.SomosmasApplicationTests;
import org.alkemy.somosmas.security.JWTResponse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthControllerLoginTest extends SomosmasApplicationTests {

    @Test
    void postLogin_withValidLoginRequest_mustReturnToken() throws IOException {

        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/login/request/postLogin_withValidLoginRequest_mustReturnToken.json")));
        var headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));

        var httpEntity = new HttpEntity(request, headers);

        var response = restTemplate.exchange("/auth/login", HttpMethod.POST, httpEntity, JWTResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getToken()).isNotNull().contains("ey");

    }

    @Test
    void postLogin_withInvalidLoginRequest_statusCodeMustBeForbidden() throws IOException {

        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/login/request/postLogin_withInvalidLoginRequest_statusCodeMustBeForbidden.json")));
        var headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));

        var httpEntity = new HttpEntity(request, headers);

        var response = restTemplate.exchange("/auth/login", HttpMethod.POST, httpEntity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(response.getBody()).contains("message").contains("The email or password are not correct.");

    }

}
