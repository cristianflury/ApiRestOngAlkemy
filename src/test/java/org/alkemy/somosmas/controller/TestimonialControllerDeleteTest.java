package org.alkemy.somosmas.controller;


import com.amazonaws.services.s3.model.PutObjectResult;
import org.alkemy.somosmas.SomosmasApplicationTests;
import org.alkemy.somosmas.model.Testimonial;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class TestimonialControllerDeleteTest extends SomosmasApplicationTests {


    @Test
    void delete_a_testimonial_without_auth_returns_forbidden() throws IOException {

        long id = createTestimonialAndReturnId();
        var httpHeaders = new HttpHeaders();
        var httpEntity = new HttpEntity(httpHeaders);
        var response = restTemplate.exchange("/testimonials/"+ id, HttpMethod.DELETE, httpEntity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);

        verify(amazonS3, never()).deleteObject(any());

    }
    @Test
    void delete_a_testimonial_without_admin_role_returns_forbidden() throws IOException {
        long id = createTestimonialAndReturnId();
        var httpHeaders = new HttpHeaders(); //postLogin_User_Role_User.json
        httpHeaders.set("Authorization",obtainJWT("./src/test/resources/harness/register/request/postLogin_User_Role_User.json"));
        var httpEntity = new HttpEntity(httpHeaders);
        var response = restTemplate.exchange("/testimonials/"+ id, HttpMethod.DELETE, httpEntity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);

        verify(amazonS3, never()).deleteObject(any());
    }
    @Test
    void delete_a_testimonial_with_admin_role_works() throws IOException {
        long id = createTestimonialAndReturnId();
        var httpHeaders = new HttpHeaders(); //postLogin_User_Role_User.json
        httpHeaders.set("Authorization",obtainJWT("./src/test/resources/harness/register/request/postLogin_User_Role_Admin.json"));
        var httpEntity = new HttpEntity(httpHeaders);
        var response = restTemplate.exchange("/testimonials/"+ id, HttpMethod.DELETE, httpEntity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        verify(amazonS3, times(1)).deleteObject(any());
    }
    @Test
    void delete_a_testimonial_that_does_not_exists_fails() throws IOException {
        long id = 10L;
        var httpHeaders = new HttpHeaders(); //postLogin_User_Role_User.json
        httpHeaders.set("Authorization",obtainJWT("./src/test/resources/harness/register/request/postLogin_User_Role_Admin.json"));
        var httpEntity = new HttpEntity(httpHeaders);
        var response = restTemplate.exchange("/testimonials/"+ id, HttpMethod.DELETE, httpEntity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        verify(amazonS3, never()).deleteObject(any());
    }
    private Long createTestimonialAndReturnId() throws IOException {
        when(amazonS3.putObject(any())).thenReturn(mock(PutObjectResult.class));
        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/controller/testimonial/request/create_testimonial_with_proper_values.json")));
        var headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));
        headers.set("Authorization",obtainJWT("./src/test/resources/harness/register/request/postLogin_User_Role_Admin.json"));

        var httpEntity = new HttpEntity(request, headers);
        var response = restTemplate.exchange("/testimonials/", HttpMethod.POST, httpEntity, String.class);
        var testimonialList =entityManager.createQuery("select u from Testimonial u where u.name='pepe argento'").getResultList();
        Testimonial testimonial = (Testimonial) testimonialList.get(0);
        return testimonial.getId();
    }
}
