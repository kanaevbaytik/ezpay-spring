package com.ezpay.ezpay.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExist extends ResponseStatusException {
    public UserAlreadyExist(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}