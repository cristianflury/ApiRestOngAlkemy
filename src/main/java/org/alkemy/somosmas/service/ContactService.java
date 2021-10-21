package org.alkemy.somosmas.service;

import org.alkemy.somosmas.model.Contact;
import java.util.List;
import java.io.IOException;

public interface ContactService {
    List<Contact> getContactList();
    Contact create(Contact contact) throws IOException;
}
