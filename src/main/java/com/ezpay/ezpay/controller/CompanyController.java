package com.ezpay.ezpay.controller;

import com.ezpay.ezpay.domains.dto.request.CompanyDtoRequest;
import com.ezpay.ezpay.domains.dto.request.TransactionDtoRequest;
import com.ezpay.ezpay.domains.dto.response.CompanyApiKeys;
import com.ezpay.ezpay.domains.entity.Transaction;
import com.ezpay.ezpay.domains.entity.User;
import com.ezpay.ezpay.service.imp.CompanyService;
import com.ezpay.ezpay.service.imp.TransactionService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CompanyController {
    CompanyService companyService;
    TransactionService transactionService;

    @PostMapping
    public CompanyApiKeys createCompany(
            @RequestBody CompanyDtoRequest companyDtoRequest,
            @AuthenticationPrincipal User user
    ) {
        System.out.println(user);
        return companyService.createCompany(companyDtoRequest, user);
    }

    @PostMapping("/{company-id}")
    public Transaction createNewTransaction(@PathVariable("company-id") UUID companyId, @RequestBody TransactionDtoRequest request) {
        return transactionService.createTransaction(request, companyId);
    }
}
