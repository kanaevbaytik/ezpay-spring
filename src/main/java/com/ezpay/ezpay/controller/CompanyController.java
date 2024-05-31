package com.ezpay.ezpay.controller;

import com.ezpay.ezpay.domains.dto.request.CompanyDtoRequest;
import com.ezpay.ezpay.domains.dto.response.CompanyApiKeys;
import com.ezpay.ezpay.domains.entity.User;
import com.ezpay.ezpay.service.imp.CompanyService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpHeaders;
import java.security.Principal;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CompanyController {
    CompanyService companyService;

    @PostMapping("/create")
    public CompanyApiKeys createCompany(@RequestBody CompanyDtoRequest companyDtoRequest, @AuthenticationPrincipal User user){
        return companyService.createCompany(companyDtoRequest, user);
    }
}
