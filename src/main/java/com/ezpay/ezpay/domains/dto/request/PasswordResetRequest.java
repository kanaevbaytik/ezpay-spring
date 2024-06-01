package com.ezpay.ezpay.domains.dto.request;

public record PasswordResetRequest(
        String code,
        String newPassword
) {
}
