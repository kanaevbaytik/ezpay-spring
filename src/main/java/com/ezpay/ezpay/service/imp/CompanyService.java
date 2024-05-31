package com.ezpay.ezpay.service.imp;

import com.ezpay.ezpay.domains.dto.request.CompanyDtoRequest;
import com.ezpay.ezpay.domains.dto.response.CompanyApiKeys;
import com.ezpay.ezpay.domains.entity.Company;
import com.ezpay.ezpay.domains.entity.User;
import com.ezpay.ezpay.repository.CompanyRepository;
import com.ezpay.ezpay.security.JwtService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Data
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class CompanyService {
    CompanyRepository companyRepository;
    JwtService jwtService;
    @Autowired
    public CompanyService(CompanyRepository companyRepository, JwtService jwtService) {
        this.companyRepository = companyRepository;
        this.jwtService = jwtService;
    }
    public CompanyApiKeys createCompany(CompanyDtoRequest companyDtoRequest, User user){
        try {
            Company company = Company.builder()
                    .companyName(companyDtoRequest.getCompanyName())
                    .createDate(LocalDateTime.now())
                    .email(companyDtoRequest.getEmail())
                    .taxId(companyDtoRequest.getTaxId())
                    .businessType(companyDtoRequest.getBusinessType())
                    .supportPhone(companyDtoRequest.getSupportPhone())
                    .price(companyDtoRequest.getPrice())
                    .addressCompany(companyDtoRequest.getAddressCompany())
                    .currency(companyDtoRequest.getCurrency())
                    .user(user)
                    .build();
            companyRepository.save(company);
            return CompanyApiKeys.builder()
                    .key("non")
                    .build();
        }catch (Exception e){
            return null;
        }
    }
    CompanyApiKeys chaneSettingsCompany(CompanyDtoRequest companyDtoRequest,String token){
        try {
            User user = companyRepository.findUserByUsername(jwtService.extractUsername(token)).getUser();
            Company company = Company.builder()
                    .companyName(companyDtoRequest.getCompanyName())
                    .createDate(LocalDateTime.now())
                    .email(companyDtoRequest.getEmail())
                    .taxId(companyDtoRequest.getTaxId())
                    .businessType(companyDtoRequest.getBusinessType())
                    .supportPhone(companyDtoRequest.getSupportPhone())
                    .price(companyDtoRequest.getPrice())
                    .addressCompany(companyDtoRequest.getAddressCompany())
                    .currency(companyDtoRequest.getCurrency())
                    .user(user)
                    .build();
            companyRepository.save(company);
            return CompanyApiKeys.builder()
                    .key("non")
                    .build();
        }catch (Exception e){
            return null;
        }
    }
    void deleteCompany(String token, String companyName){
        companyRepository.deleteByCompanyName(companyName);
    }





}
