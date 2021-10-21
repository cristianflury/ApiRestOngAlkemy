package org.alkemy.somosmas.service.impl;

import lombok.RequiredArgsConstructor;
import org.alkemy.somosmas.model.Organization;
import org.alkemy.somosmas.model.User;
import org.alkemy.somosmas.service.MailContentBuilderService;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RequiredArgsConstructor
@Service
public class MailContentBuilderServiceImpl implements MailContentBuilderService {

    private final TemplateEngine templateEngine;

    @Override
    public String buildWelcomeEmail(User newUser) {

        Context ctx = new Context();
        ctx.setVariable("user", newUser);
        ctx.setVariable("organization", newUser.getOrganization());
        return templateEngine.process("welcome", ctx);

    }
    @Override
    public String buildContactResponse(String name, Organization organization) {
        Context ctx = new Context();
        ctx.setVariable("name", name);
        ctx.setVariable("organization", organization);
        return templateEngine.process("contactResponse", ctx);
    }

}