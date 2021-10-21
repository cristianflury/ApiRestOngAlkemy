package org.alkemy.somosmas.configuration;


import com.sendgrid.SendGrid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SendgridConfiguration {


    @Bean
    public SendGrid getInstanceSendgrid(@Value("${sendgrid.api.key}") String apikey) {

        return new SendGrid(apikey);
    }

}
