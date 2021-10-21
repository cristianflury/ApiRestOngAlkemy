package org.alkemy.somosmas.controller;

import com.amazonaws.services.s3.model.PutObjectResult;
import org.alkemy.somosmas.SomosmasApplicationTests;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestimonialControllerCreateTest extends SomosmasApplicationTests {
    @Test
    void create_testimonial_without_name_returns_BAD_REQUEST() throws IOException {

        when(amazonS3.putObject(any())).thenReturn(mock(PutObjectResult.class));

        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/controller/testimonial/request/create_testimonial_without_name_returns_bad_request.json")));
        var headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));
        headers.set("Authorization",obtainJWT("./src/test/resources/harness/register/request/postLogin_User_Role_User.json"));
        var httpEntity = new HttpEntity(request, headers);

        var response = restTemplate.exchange("/testimonials/", HttpMethod.POST, httpEntity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        verify(amazonS3, never()).putObject(any());
    }
    @Test
    void create_testimonial_without_content_returns_BAD_REQUEST() throws IOException {

        when(amazonS3.putObject(any())).thenReturn(mock(PutObjectResult.class));

        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/controller/testimonial/request/create_testimonial_without_content_returns_bad_request.json")));
        var headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));
        headers.set("Authorization",obtainJWT("./src/test/resources/harness/register/request/postLogin_User_Role_User.json"));
        var httpEntity = new HttpEntity(request, headers);

        var response = restTemplate.exchange("/testimonials/", HttpMethod.POST, httpEntity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        verify(amazonS3, never()).putObject(any());
    }
    @Test
    void create_testimonial_without_Auth_returns_FORBIDDEN() throws IOException {

        when(amazonS3.putObject(any())).thenReturn(mock(PutObjectResult.class));

        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/controller/testimonial/request/create_testimonial_with_proper_values.json")));
        var headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));
        var httpEntity = new HttpEntity(request, headers);

        var response = restTemplate.exchange("/testimonials/", HttpMethod.POST, httpEntity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        verify(amazonS3, never()).putObject(any());
    }
    @Test
    void create_testimonial_with_User_Role_returns_FORBIDDEN() throws IOException {

        when(amazonS3.putObject(any())).thenReturn(mock(PutObjectResult.class));
        var previousTestimonialsCreatedByThisUser = entityManager.createQuery("select u from Testimonial u where u.name='pepe argento'").getResultList().size();

        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/controller/testimonial/request/create_testimonial_with_proper_values.json")));
        var headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));
        headers.set("Authorization",obtainJWT("./src/test/resources/harness/register/request/postLogin_User_Role_User.json"));
        var httpEntity = new HttpEntity(request, headers);

        var response = restTemplate.exchange("/testimonials/", HttpMethod.POST, httpEntity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        var allTestimonialsCreatedByThisUser = entityManager.createQuery("select u from Testimonial u where u.name='pepe argento'").getResultList().size();
        assertEquals(previousTestimonialsCreatedByThisUser,allTestimonialsCreatedByThisUser);
        verify(amazonS3, never()).putObject(any());
    }
    @Test
    void create_testimonial_with_Admin_Role_returns_CREATED() throws IOException {

        when(amazonS3.putObject(any())).thenReturn(mock(PutObjectResult.class));
        var previousTestimonialsCreatedByThisUserCount = entityManager.createQuery("select u from Testimonial u where u.name='pepe argento'").getResultList().size();

        var request = new String(Files.readAllBytes(Paths.get("./src/test/resources/harness/controller/testimonial/request/create_testimonial_with_proper_values.json")));
        var headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));
        headers.set("Authorization",obtainJWT("./src/test/resources/harness/register/request/postLogin_User_Role_Admin.json"));

        var httpEntity = new HttpEntity(request, headers);
        var response = restTemplate.exchange("/testimonials/", HttpMethod.POST, httpEntity, String.class);
        var allTestimonialsCreatedByThisUserCount = entityManager.createQuery("select u from Testimonial u where u.name='pepe argento'").getResultList().size();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(previousTestimonialsCreatedByThisUserCount + 1,allTestimonialsCreatedByThisUserCount);
        verify(amazonS3, times(1)).putObject(any());
    }
}
