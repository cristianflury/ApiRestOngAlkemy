package org.alkemy.somosmas;

import com.amazonaws.services.s3.AmazonS3;
import com.sendgrid.SendGrid;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import javax.persistence.EntityManager;

@SpringBootTest
public abstract class SomosMasUnitTests {
    @Autowired
    protected EntityManager entityManager;

    @MockBean
    protected SendGrid sendGrid;

    @MockBean
    protected AmazonS3 amazonS3;

}
