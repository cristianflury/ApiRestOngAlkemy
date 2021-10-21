package org.alkemy.somosmas.service;

import org.alkemy.somosmas.model.Organization;
import org.alkemy.somosmas.model.User;

public interface MailContentBuilderService {

    String buildWelcomeEmail(User newUser);

    String buildContactResponse(String name, Organization organization);
}