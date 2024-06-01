package com.ezpay.ezpay.repository;

import com.ezpay.ezpay.domains.entity.Company;
import com.ezpay.ezpay.domains.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.company.id = :companyId")
    BigDecimal getTotalAmountForCompany(@Param("companyId") UUID companyId);
    Transaction findByCompanyId(Company userById);
    @Query("SELECT SUM(t.amount) FROM Transaction t")
    BigDecimal getTotalAmount();
}

