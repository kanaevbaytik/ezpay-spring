package com.ezpay.ezpay.repository;

import com.ezpay.ezpay.domains.entity.Company;
import com.ezpay.ezpay.domains.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT t.company FROM Transaction t WHERE t.id = :transactionId")
    Company findCompanyByTransactionId(@Param("transactionId") UUID transactionId);
}
