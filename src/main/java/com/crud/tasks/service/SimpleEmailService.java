package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {

    private final JavaMailSender javaMailSender;
    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(final Mail mail) {
        log.info("Starting email preparation....");

        try {
            // SimpleMailMessage mailMessage = createMailMessage(mail);
            // javaMailSender.send(mailMessage);
            javaMailSender.send(createMimeMessage(mail));
            log.info("Email has been sent");
        } catch (MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }

    //private SimpleMailMessage createMailMessage(final Mail mail) {
    //   SimpleMailMessage mailMessage = new SimpleMailMessage();
    // mailMessage.setTo(mail.getMail());
    // mailMessage.setSubject(mail.getSubject());
    //mailMessage.setText(mail.getMessage());
    // if (mail.getToCc() != null && !mail.getToCc().isEmpty()) {
    //     mailMessage.setCc(mail.getToCc());
    // }
    // return mailMessage;
    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMail());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
        };

    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMail());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()));
        if (mail.getToCc() != null && !mail.getToCc().isEmpty()) {
            mailMessage.setCc(mail.getToCc());
        }
            return mailMessage;
        }
    }

