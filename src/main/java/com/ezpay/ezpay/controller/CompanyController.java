package com.ezpay.ezpay.controller;

import com.ezpay.ezpay.domains.dto.request.CompanyDtoRequest;
import com.ezpay.ezpay.domains.dto.request.TransactionDtoRequest;
import com.ezpay.ezpay.domains.dto.response.CompanyApiKeys;
import com.ezpay.ezpay.domains.dto.response.TransactionDto;
import com.ezpay.ezpay.domains.entity.Transaction;
import com.ezpay.ezpay.domains.dto.response.CompanyDto;
import com.ezpay.ezpay.domains.entity.User;
import com.ezpay.ezpay.service.imp.CompanyService;
import com.ezpay.ezpay.service.imp.TransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

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

    @PostMapping("/{company-id}/new-transaction")
    public TransactionDto createNewTransaction(@PathVariable("company-id") UUID companyId, @RequestBody TransactionDtoRequest request) {
        return transactionService.createTransaction(request, companyId);
    }

    @GetMapping
    public List<CompanyDto> getAllCompanies(@AuthenticationPrincipal User user) {
        return companyService.getAllCompaniesByUser(user);
    }
}
