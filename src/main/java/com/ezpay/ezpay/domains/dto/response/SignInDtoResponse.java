package com.ezpay.ezpay.domains.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignInDtoResponse {
    private String firstName;
    private String lastName;
    private String phone;
    private String username;
    private String avatarUrl;
}
