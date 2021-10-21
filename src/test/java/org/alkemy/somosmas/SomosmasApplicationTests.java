package org.alkemy.somosmas;

import com.amazonaws.services.s3.AmazonS3;
import com.sendgrid.SendGrid;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class SomosmasApplicationTests {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected EntityManager entityManager;

    @MockBean
    protected SendGrid sendGrid;

    @MockBean
    protected AmazonS3 amazonS3;

   protected String obtainJWT(String json) throws IOException {
        var headers = new HttpHeaders();
        headers.put(HttpHeaders.CONTENT_TYPE, Lists.newArrayList(MediaType.APPLICATION_JSON_VALUE));
        var requestLogin = new String(Files.readAllBytes(Paths.get(json)));
        var httpEntityForLogin = new HttpEntity(requestLogin, headers);
        var token = restTemplate.exchange("/auth/login", HttpMethod.POST, httpEntityForLogin, String.class);
        var tokenClean = token.getBody().split("\"")[3];
        return "Bearer " + tokenClean;
    }

    protected HttpHeaders getAdminHeader() throws IOException {
        // USER => email='noah_smith@gmail.com' role='ROLE_ADMIN'
        var token = obtainJWT("./src/test/resources/harness/register/request/postLogin_User_Role_Admin.json");
        var headersAuth = new HttpHeaders();
        headersAuth.set("Authorization", token);
        return headersAuth;
    }

    protected HttpHeaders getUserHeader() throws IOException {
        // USER => email='elijah_lopez@gmail.com' role='ROLE_USER'
        var token = obtainJWT("./src/test/resources/harness/register/request/postLogin_User_Role_User.json");
        var headersAuth = new HttpHeaders();
        headersAuth.set("Authorization", token);
        return headersAuth;
    }


}
