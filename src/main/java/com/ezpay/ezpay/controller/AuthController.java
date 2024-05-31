package com.ezpay.ezpay.controller;

import com.ezpay.ezpay.domains.dto.request.UserSignInDtoRequest;
import com.ezpay.ezpay.domains.dto.request.UserSignUpDtoRequest;
import com.ezpay.ezpay.domains.dto.response.LoginResponse;
import com.ezpay.ezpay.domains.dto.response.SignInDtoResponse;
import com.ezpay.ezpay.service.AuthService;
import com.ezpay.ezpay.service.imp.UserAuthServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService<UserSignInDtoRequest, UserSignUpDtoRequest> authService;
    UserAuthServiceImpl userAuthService;

    @PostMapping("/signIn")
    public LoginResponse signIn(@RequestBody UserSignInDtoRequest userSignInDtoRequest){
        return LoginResponse.builder()
                .token(userAuthService.signIn(userSignInDtoRequest))
                .build();
    }

    @PostMapping("/signUp")
    public LoginResponse signUp(@RequestBody UserSignInDtoRequest request) {
        String token = authService.signIn(request);
        return LoginResponse.builder()
                .token(token)
                .build();
    }
    @PostMapping("/resetPassword")
    public RequestEntity<String> resetPassword(){
        return null;
    }
    @PostMapping("/refreshToken")
    public RequestEntity<String> refreshToken(){
        return null;
    }
}
