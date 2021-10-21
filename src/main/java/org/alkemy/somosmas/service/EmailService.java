package org.alkemy.somosmas.service;

import com.sendgrid.Response;
import org.alkemy.somosmas.service.bo.MailBO;


import java.io.IOException;


public interface EmailService {

    Response send(MailBO mailBO) throws IOException;
}
