package com.ezpay.ezpay.domains.dto.response;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CompanyDto(
        UUID id,
        String email,
        String companyName,
        String businessType,
        String taxId,
        String supportPhone,
        LocalDateTime createDate,
        BigDecimal price,
        String addressCompany,
        String statementDescription,
        String currency
) {
}
