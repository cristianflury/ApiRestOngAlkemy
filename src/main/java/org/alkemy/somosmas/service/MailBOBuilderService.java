package org.alkemy.somosmas.service;

import org.alkemy.somosmas.model.Contact;
import org.alkemy.somosmas.model.User;
import org.alkemy.somosmas.service.bo.MailBO;

import java.io.IOException;

public interface MailBOBuilderService {

     void sendWelcome(User user)throws IOException;

    void sendContactResponse(Contact contact) throws IOException;
}
