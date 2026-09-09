package com.aspire.authservice.service.impl;

import com.aspire.authservice.exception.AuthServiceApplicationException;
import com.aspire.authservice.service.EmailService;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;




@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final Session session;

    @Value("${mail.username}")
    private String  from;

    @Override
    public void sendEmail(String to, String subject, String body) {

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.setRecipient(Message.RecipientType.TO , new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(body);
            Transport.send(mimeMessage);

        } catch (Exception e) {

            throw new AuthServiceApplicationException("Failed to sent email", e);

        }
    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String body, String filename, byte[] fileByte) {
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            mimeMessage.setSubject(subject);
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(body);
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource dataSource =new ByteArrayDataSource(fileByte,"application/vnd.openxmlformats-officedocument.spreadsheetxml.sheet");

            attachmentPart.setDataHandler(new DataHandler(dataSource));
            attachmentPart.setFileName(filename);
            multipart.addBodyPart(attachmentPart);
            mimeMessage.setContent(multipart);
            Transport.send(mimeMessage);


        } catch (Exception e) {

        }
    }
}
