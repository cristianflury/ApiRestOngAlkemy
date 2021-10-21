package org.alkemy.somosmas.controller;


import org.alkemy.somosmas.model.Contact;
import org.alkemy.somosmas.repository.ContactRepository;
import org.alkemy.somosmas.repository.UserRepository;
import org.alkemy.somosmas.service.ContactService;
import org.alkemy.somosmas.service.MailBOBuilderService;
import org.alkemy.somosmas.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



public class ContactServiceUnitTest {

    private MailBOBuilderService mailBOBuilderService;
    private UserRepository userRepository;
    private ContactRepository contactRepository;
    private ContactService contactService;


    @BeforeEach
    void beforeEach() {
        mailBOBuilderService = Mockito.mock(MailBOBuilderService.class);
        userRepository = Mockito.mock(UserRepository.class);
        contactRepository = Mockito.mock(ContactRepository.class);
        contactService = new ContactServiceImpl(contactRepository, mailBOBuilderService, userRepository);

    }

    @Test
    void createContactAndSendEmail() throws IOException {

        Contact contactMock = new Contact();
        contactMock.setId(1L);
        contactMock.setEmail("pepe@email.com");
        contactMock.setMessage("Hola, una consulta");
        contactMock.setName("Pepe");
        when(contactRepository.save(contactMock)).thenReturn(contactMock);


        Contact contactResponse = contactService.create(contactMock);

        assertThat(contactResponse.getName()).isEqualTo("Pepe");
        assertThat(contactResponse.getEmail()).isEqualTo("pepe@email.com");
        verify(mailBOBuilderService,times(1)).sendContactResponse(any());
    }



}
