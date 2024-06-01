package com.ezpay.ezpay.service;

public interface MailSender {
    void sendPasswordResetMessage(String to);
    void send(String to, String message);
}
