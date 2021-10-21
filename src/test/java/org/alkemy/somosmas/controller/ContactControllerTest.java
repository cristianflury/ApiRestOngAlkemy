package org.alkemy.somosmas.controller;



import com.sendgrid.Response;
import org.alkemy.somosmas.SomosmasApplicationTests;
import org.alkemy.somosmas.dto.AuthenticationLoginRequestDto;
import org.alkemy.somosmas.model.Contact;
import org.alkemy.somosmas.security.JWTResponse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ContactControllerTest extends SomosmasApplicationTests {

    @Autowired
    private AuthController authController;

    @Test
    void postCreate_withFieldsValid_mustSaveNewContactAndSendEmail() throws IOException {


        when(sendGrid.api(any())).thenReturn(mock(Response.class));
        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/contact/request/postCreate_withFieldValid_mustSaveNewContactAndSendEmail.json")));
        var headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));

        var httpEntity = new HttpEntity(request, headers);


        var response = restTemplate.exchange("/contacts", HttpMethod.POST, httpEntity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Cristian");

        var contact = (Contact) entityManager.createQuery("select c from Contact c where c.name='Cristian'").getSingleResult();

        assertThat(contact.getId()).isNotNull();
        assertThat(contact.getCreatedAt()).isNotNull();
        assertThat(contact.getEmail()).isEqualTo("cristian@email.com");

        verify(sendGrid, times(1) ).api(any());
    }

    @Test
    void errorPostCreate_withoutFieldsValid() throws IOException {


        when(sendGrid.api(any())).thenReturn(mock(Response.class));
        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/contact/request/errorPostCreate_withoutFieldsValid.json")));
        var headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));

        var httpEntity = new HttpEntity(request, headers);


        var response = restTemplate.exchange("/contacts", HttpMethod.POST, httpEntity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("message")
                .contains("vacío")
                .contains("correo");

        verify(sendGrid, never() ).api(any());
    }

    @Test
    void getContactList_WithRoleUser(){
        AuthenticationLoginRequestDto loginRequestDto =
                new AuthenticationLoginRequestDto("felipe_hernandez@gmail.com","felipe123");

        ResponseEntity<JWTResponse>  responseEntity =  authController.login(loginRequestDto);

        var headers = new HttpHeaders();
        headers.setBearerAuth( responseEntity.getBody().getToken());
        headers.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));
        var httpEntity = new HttpEntity(headers);
        var response = restTemplate.exchange("/contacts", HttpMethod.GET, httpEntity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);

    }
    @Test
    void getContactList_WithRoleAdmin(){
        AuthenticationLoginRequestDto loginRequestDto =
                new AuthenticationLoginRequestDto("noah_smith@gmail.com","noah123");

        ResponseEntity<JWTResponse>  responseEntity =  authController.login(loginRequestDto);

        var headers = new HttpHeaders();
        headers.setBearerAuth( responseEntity.getBody().getToken());
        headers.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));
        var httpEntity = new HttpEntity(headers);
        var response = restTemplate.exchange("/contacts", HttpMethod.GET, httpEntity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

    }

}