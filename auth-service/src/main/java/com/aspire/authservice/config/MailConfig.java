package com.aspire.authservice.config;


import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${email.host}")
    private String host;

    @Value("${email.port}")
    private  int port ;

    @Value("${email.username}")
    private String username;

    @Value("${email.password}")
    private String password;

    @Value("${email.debug}")
    private boolean debug;

    @Bean
    public Session session(){
        Properties pros = new Properties();
        pros.put("mail.smtp.host",host);
        pros.put("mail.smpt.port",port);
        String.valueOf(port);
        pros.put("mail.smtp.auth",true);
        pros.put("mail.smtp.ssl.enable",true);
        pros.put("mail.debug",String.valueOf(debug));

        return  Session.getInstance(pros, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
    }


}
