package com.ezpay.ezpay.service.imp;

import com.ezpay.ezpay.domains.entity.PasswordReset;
import com.ezpay.ezpay.domains.entity.User;
import com.ezpay.ezpay.repository.PasswordResetRepo;
import com.ezpay.ezpay.repository.UserRepository;
import com.ezpay.ezpay.service.MailSender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
@RequiredArgsConstructor
public class MailSenderImpl implements MailSender {
    final JavaMailSender mailSender;
    final PasswordResetRepo passwordResetRepo;

    @Value("${spring.mail.username}")
    String fromEmail;

    @Async
    @Override
    public void sendPasswordResetMessage(String to) {
        try {
            String code =  UUID.randomUUID().toString();
            PasswordReset passwordReset = new PasswordReset();
            passwordReset.setUsername(to);
            passwordReset.setCode(code);
            passwordResetRepo.save(passwordReset);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setSubject("Сброс пароля");
            message.setTo(to);
            message.setText("Здравствуйте, " + to + ", для сброса пароля перейдите по ссылке: " + "http://localhost:8080/auth/resetPassword?code=" + code);
            mailSender.send(message);
        } catch (Exception ioException) {
            throw new MailSendException(ioException.getMessage());
        }
    }

    @Override
    public void send(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setSubject("Сброс пароля");
        message.setTo(to);
        message.setText("Ваш новый пароль: " + text);
        mailSender.send(message);
    }
}
