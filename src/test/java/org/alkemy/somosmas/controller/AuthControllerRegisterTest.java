package org.alkemy.somosmas.controller;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.sendgrid.Response;
import org.alkemy.somosmas.SomosmasApplicationTests;
import org.alkemy.somosmas.model.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthControllerRegisterTest extends SomosmasApplicationTests {

    @Test
    void postRegister_withValidUser_mustSaveNewUserAndSendMail() throws IOException {

        //Given
        when(sendGrid.api(any())).thenReturn(mock(Response.class));
        when(amazonS3.putObject(any())).thenReturn(mock(PutObjectResult.class));

        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/register/request/postRegister_withValidUser_mustSaveNewUserAndSendMail.json")));
        var headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));

        var httpEntity = new HttpEntity(request, headers);

        //When
        var response = restTemplate.exchange("/auth/register", HttpMethod.POST, httpEntity, String.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("token").contains("ey");
        var user = (User) entityManager.createQuery("select u from User u where u.email='pepito@email.com'").getSingleResult();

        assertThat(user.getId()).isNotNull();
        assertThat(user.getCreatedAt()).isNotNull();
        assertThat(user.getPassword()).isNotEqualTo("pepito");
        assertThat(user.getPhoto()).isNotNull();
        assertThat(user.getDeleted()).isFalse();
        assertThat(user.getRole().getName()).isEqualTo("ROLE_USER");

        verify(sendGrid, times(1)).api(any());
        verify(amazonS3, times(1)).putObject(any());
    }

    @Test
    void postRegister_withUnavailableEmail_statusCodeMustBeBadRequest() throws IOException {

        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/register/request/postRegister_withUnavailableEmail_mustRaiseException.json")));
        var headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));

        var httpEntity = new HttpEntity(request, headers);

        //When
        var response = restTemplate.exchange("/auth/register", HttpMethod.POST, httpEntity, String.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("message").contains("Error: email ya registrado");

    }

}
