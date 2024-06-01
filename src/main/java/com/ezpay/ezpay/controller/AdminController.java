package com.ezpay.ezpay.controller;

import com.ezpay.ezpay.service.imp.TransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AdminController {
    TransactionService transactionService;
    @GetMapping("/{id}")
    public BigDecimal getTotalAmountForCompany(@PathVariable UUID companyId) {
        return transactionService.getTotalAmountForCompany(companyId);
    }
    @GetMapping("/getTotalAmount")
    public BigDecimal getTotalAmount() {
        return transactionService.getTotalAmount();
    }
}
