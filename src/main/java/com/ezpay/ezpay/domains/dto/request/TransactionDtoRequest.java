package com.ezpay.ezpay.domains.dto.request;

import com.ezpay.ezpay.domains.enums.Currency;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDtoRequest {
    BigDecimal amount;
    String clientUsername;
    Currency currency;
    String cardNumber;
}
