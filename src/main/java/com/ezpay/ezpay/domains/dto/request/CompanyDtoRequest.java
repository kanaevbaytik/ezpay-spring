package com.ezpay.ezpay.domains.dto.request;

import com.ezpay.ezpay.domains.enums.Currency;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyDtoRequest {
    String email;
    String companyName;
    String businessType;
    Currency currency;
    String addressCompany;
    String statementDescription;
    String taxId;
    String supportPhone;
    LocalDateTime createDate;
    BigDecimal price;
}
