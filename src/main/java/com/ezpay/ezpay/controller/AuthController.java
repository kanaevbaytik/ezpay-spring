package com.ezpay.ezpay.controller;

import com.ezpay.ezpay.domains.dto.request.PasswordResetRequest;
import com.ezpay.ezpay.domains.dto.request.UserSignInDtoRequest;
import com.ezpay.ezpay.domains.dto.request.UserSignUpDtoRequest;
import com.ezpay.ezpay.domains.dto.response.LoginResponse;
import com.ezpay.ezpay.domains.dto.response.SignInDtoResponse;
import com.ezpay.ezpay.service.AuthService;
import com.ezpay.ezpay.service.MailSender;
import com.ezpay.ezpay.service.imp.UserAuthServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
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
    MailSender mailSender;

    @SecurityRequirements
    @PostMapping("/signIn")
    public LoginResponse signIn(@RequestBody UserSignInDtoRequest userSignInDtoRequest){
        return LoginResponse.builder()
                .token(userAuthService.signIn(userSignInDtoRequest))
                .build();
    }

    @SecurityRequirements
    @PostMapping("/signUp")
    public LoginResponse signUp(@RequestBody UserSignUpDtoRequest request) {
        String token = authService.signUp(request);
        return LoginResponse.builder()
                .token(token)
                .build();
    }

    @SecurityRequirements
    @GetMapping("/attemptToReset")
    public void attemptToReset(@RequestParam("to") String to) {
        mailSender.sendPasswordResetMessage(to);
    }

    @SecurityRequirements
    @GetMapping("/resetPassword")
    public String resetPassword(@RequestParam("code") String code){
       return authService.resetPassword(code);
    }

    @SecurityRequirements
    @PostMapping("/refreshToken")
    public RequestEntity<String> refreshToken(){
        return null;
    }
}
