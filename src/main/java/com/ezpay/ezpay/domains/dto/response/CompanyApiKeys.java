package com.ezpay.ezpay.domains.dto.response;

import lombok.Builder;

@Builder
public record CompanyApiKeys(
        String key
) {
}
