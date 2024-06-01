package com.ezpay.ezpay.controller;

import com.ezpay.ezpay.domains.dto.request.UserSignInDtoRequest;
import com.ezpay.ezpay.domains.dto.request.UserSignUpDtoRequest;
import com.ezpay.ezpay.domains.dto.response.SignInDtoResponse;
import com.ezpay.ezpay.service.AuthService;
import com.ezpay.ezpay.service.imp.UserAuthServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MeController {
    UserAuthServiceImpl userAuthService;
    AuthService<UserSignInDtoRequest, UserSignUpDtoRequest> authService;

    @GetMapping("/{token}")
    public SignInDtoResponse getMe(@PathVariable String token){
        return authService.getMe(token);
    }
}
