package org.alkemy.somosmas.service.impl;

import lombok.RequiredArgsConstructor;

import org.alkemy.somosmas.model.Contact;
import org.alkemy.somosmas.model.User;
import org.alkemy.somosmas.repository.OrganizationRepository;
import org.alkemy.somosmas.service.EmailService;
import org.alkemy.somosmas.service.MailBOBuilderService;
import org.alkemy.somosmas.service.MailContentBuilderService;
import org.alkemy.somosmas.service.bo.MailBO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class MailBOBuilderServiceImpl implements MailBOBuilderService {

    private final MailContentBuilderService mailContentBuilderService;
    private final OrganizationRepository organizationRepository;
    private final EmailService emailService;
    private final String CONTACT_RESPONSE_EMAIL_SUBJECT = "Gracias por tu contacto!";


    private MailBO build(User user){
        MailBO mailBO = new MailBO();
        mailBO.setTo(user.getEmail());

        return  mailBO;
    }


    public void sendWelcome(User user)throws IOException {
        MailBO  mailBO = build(user );

        mailBO.setSubject(user.getOrganization().getWelcomeText());
        mailBO.setValueContent(mailContentBuilderService.buildWelcomeEmail(user));

        emailService.send(mailBO);
    }

    @Override
    public void sendContactResponse(Contact contact) throws IOException {
        MailBO mailBO = new MailBO();
        mailBO.setTo(contact.getEmail());
        mailBO.setSubject(CONTACT_RESPONSE_EMAIL_SUBJECT);
        mailBO.setValueContent(mailContentBuilderService.buildContactResponse(contact.getName(), contact.getOrganization()));
        emailService.send(mailBO);
    }
}
