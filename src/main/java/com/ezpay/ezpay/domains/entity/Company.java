package com.ezpay.ezpay.domains.entity;

import com.ezpay.ezpay.domains.enums.Currency;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    UUID id;
    String email;
    String companyName;
    String businessType;

    String taxId;
    String supportPhone;
    LocalDateTime createDate;
    BigDecimal price;
    String addressCompany;
    String statementDescription;
    @Enumerated(value = EnumType.STRING)
    Currency currency;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    User user;

}
