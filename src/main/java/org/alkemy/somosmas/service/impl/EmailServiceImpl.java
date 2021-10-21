package org.alkemy.somosmas.service.impl;


import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.alkemy.somosmas.service.EmailService;
import org.alkemy.somosmas.service.bo.MailBO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class EmailServiceImpl implements EmailService {

    private final Method METHOD = Method.POST;
    private final String ENDPOINT = "mail/send";
    private final String TYPE_CONTENT = "text/html";
    private final SendGrid sendgrid;
    private final String from ;


    public EmailServiceImpl( @Value("${sendgrid.api.from}")String from, SendGrid sendgrid){
                this.sendgrid = sendgrid;
                this.from = from;

    }

    @Override
    public Response send(MailBO mailBO) throws IOException {

        Content content = new Content(TYPE_CONTENT, mailBO.getValueContent());
        Email from = new Email(this.from);
        Email to = new Email(mailBO.getTo());

        Mail mail = new Mail(from, mailBO.getSubject(), to, content);

        Request request = new Request();
        request.setMethod(METHOD);
        request.setEndpoint(ENDPOINT);
        request.setBody(mail.build());

        return sendgrid.api(request);

    }


}
