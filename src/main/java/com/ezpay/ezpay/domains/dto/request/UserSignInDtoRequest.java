package com.ezpay.ezpay.domains.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInDtoRequest {
    private String username;
    private String password;
}
