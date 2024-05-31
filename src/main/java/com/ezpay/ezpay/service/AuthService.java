package com.ezpay.ezpay.service;

import com.ezpay.ezpay.domains.dto.response.SignInDtoResponse;

public interface AuthService <T,M>{
    String signUp(M userDtoRequest);
    String signIn(T userDtoRequest);
    SignInDtoResponse getMe(String token);
}
