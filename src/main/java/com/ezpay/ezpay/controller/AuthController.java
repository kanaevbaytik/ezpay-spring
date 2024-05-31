package com.ezpay.ezpay.controller;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/signUp")
    public RequestEntity<String> signUp(){
        return null;
    }
    @PostMapping("/signIn")
    public RequestEntity<String> signIn(){
        return null;
    }
    @PostMapping("/resetPassword")
    public RequestEntity<String> resetPassword(){
        return null;
    }
    @PostMapping("/resetPassword")
    public RequestEntity<String> refreshToken(){
        return null;
    }
}
