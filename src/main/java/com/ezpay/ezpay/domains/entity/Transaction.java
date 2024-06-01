package com.ezpay.ezpay.domains.entity;

import com.ezpay.ezpay.domains.enums.Currency;
import com.ezpay.ezpay.domains.enums.Operations;
import com.ezpay.ezpay.domains.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "transactions")
public class Transaction {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    UUID id;
    @Enumerated(value = EnumType.STRING)
    Operations operations;
    BigDecimal amount;
    String clientUsername;
    @Enumerated(value = EnumType.STRING)
    Currency currency;
    String cardNumber;
    @Enumerated(value = EnumType.STRING)
    Status status;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    Company company;

}
