package com.ezpay.ezpay.domains.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDto(
    UUID id,
    String operation,
    BigDecimal amount,
    String clientUsername,
    String status
) {
}
