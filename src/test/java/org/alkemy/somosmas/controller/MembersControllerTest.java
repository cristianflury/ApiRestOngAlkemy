package org.alkemy.somosmas.controller;

import org.alkemy.somosmas.SomosmasApplicationTests;
import org.alkemy.somosmas.model.Activity;
import org.alkemy.somosmas.model.Member;
import org.alkemy.somosmas.model.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MembersControllerTest extends SomosmasApplicationTests {

    @Transactional
    @Test
    void registerActivity_WithValidBody() throws IOException {
        //Given

        //LOGIN HEADER
        var headersAuth = getAdminHeader();

        //TEST REQUEST
        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/members.request/postRegister_MemberWithValidBody.json")));
        headersAuth.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));
        var httpEntityWithAuth = new HttpEntity(request, headersAuth);

        //When
        var response = restTemplate.exchange("/members", HttpMethod.POST, httpEntityWithAuth, String.class);

        //Then
        var user = (User) entityManager.createQuery("select u from User u where u.email='noah_smith@gmail.com'").getSingleResult();
        var member = (Member) entityManager.createQuery("select m from Member m where m.name='Jhon'").getSingleResult();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Jhon.Salchi");
        assertThat(member.getName()).isNotNull();
        assertThat(member.getFacebookUrl()).isNotNull();
        assertThat(member.getImage()).isNotNull();
        assertThat(member.getDescription()).isNotNull();

        assertThat(response.getBody()).contains("This is me, im Jhon.");
        assertThat(user.getOrganization().getName()).isEqualTo(member.getOrganization().getName());
    }

    @Transactional
    @Test
    void registerActivity_WithInvalidBody() throws IOException {
        //Given

        //LOGIN HEADER
        var headersAuth = getAdminHeader();

        //TEST REQUEST
        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/members.request/postRegister_MemberWithInvalidBody.json")));
        headersAuth.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));
        var httpEntityWithAuth = new HttpEntity(request, headersAuth);

        //When
        var response = restTemplate.exchange("/members", HttpMethod.POST, httpEntityWithAuth, String.class);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
