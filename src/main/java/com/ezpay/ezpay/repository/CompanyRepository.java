package com.ezpay.ezpay.repository;

import com.ezpay.ezpay.domains.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    @Query("SELECT c FROM Company c WHERE c.user.username = :username")
    Company findUserByUsername(@Param("username") String username);

    void deleteByCompanyName(String companyName);
}
