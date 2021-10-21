package org.alkemy.somosmas.controller;


import org.alkemy.somosmas.SomosmasApplicationTests;
import org.alkemy.somosmas.model.Activity;
import org.alkemy.somosmas.model.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ActivitiesControllerTest extends SomosmasApplicationTests {

    @Transactional
    @Test
    void registerActivitySuccess() throws IOException {
        //Given

        //LOGIN HEADER
        var headersAuth = getAdminHeader();

        //TEST REQUEST
        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/register/request/postRegister_withValidActivity.json")));
        headersAuth.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));
        var httpEntityWithAuth = new HttpEntity(request, headersAuth);

        //When
        var response = restTemplate.exchange("/activities", HttpMethod.POST, httpEntityWithAuth, String.class);

        //Then
        var user = (User) entityManager.createQuery("select u from User u where u.email='noah_smith@gmail.com'").getSingleResult();
        var activity = (Activity) entityManager.createQuery("select a from Activity a where a.name='Good news'").getSingleResult();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("I think i perfect the plague");
        assertThat(user.getRole().getName()).isEqualTo("ROLE_ADMIN");
        assertThat(user.getOrganization().getName()).isEqualTo(activity.getOrganization().getName());
    }

    @Test
    void registerActivity_WithUserRole() throws IOException {
        //Given

        //LOGIN HEADER
        var headersAuth = getUserHeader();

        //TEST REQUEST
        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/register/request/postRegister_withValidActivity.json")));
        headersAuth.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));
        var httpEntityWithAuth = new HttpEntity(request, headersAuth);

        //When
        var response = restTemplate.exchange("/activities", HttpMethod.POST, httpEntityWithAuth, String.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        var user = (User) entityManager.createQuery("select u from User u where u.email='elijah_lopez@gmail.com'").getSingleResult();
        assertThat(user.getRole().getName()).isEqualTo("ROLE_USER");
    }

    @Test
    void registerActivity_WithInvalidRequest() throws IOException {
        //Given

        //LOGIN HEADER
        var headersAuth = getAdminHeader();

        //TEST REQUEST
        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/register/request/postRegister_withInvalidActivity.json")));
        headersAuth.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));
        var httpEntityWithAuth = new HttpEntity(request, headersAuth);

        //When
        var response = restTemplate.exchange("/activities", HttpMethod.POST, httpEntityWithAuth, String.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
