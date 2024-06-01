package com.ezpay.ezpay.service.imp;

import com.ezpay.ezpay.domains.dto.request.TransactionDtoRequest;
import com.ezpay.ezpay.domains.entity.Company;
import com.ezpay.ezpay.domains.entity.Transaction;
import com.ezpay.ezpay.domains.enums.Operations;
import com.ezpay.ezpay.domains.enums.Status;
import com.ezpay.ezpay.repository.CompanyRepository;
import com.ezpay.ezpay.repository.TransactionRepository;
import com.ezpay.ezpay.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TransactionService {
    TransactionRepository transactionRepository;
    CompanyRepository companyRepository;
    UserRepository userRepository;

    public Transaction findById(UUID id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with id: " + id));
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(TransactionDtoRequest transaction, UUID companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow();
        return transactionRepository.save(Transaction.builder()
                        .company(company)
                        .amount(transaction.getAmount())
                        .cardNumber(transaction.getCardNumber())
                        .clientUsername(transaction.getClientUsername())
                        .status(Status.OK)
                        .currency(transaction.getCurrency())
                        .operations(Operations.Payment)
                .build());
    }

    public void deleteById(UUID id) {
        transactionRepository.deleteById(id);
    }
//    public void deleteByIdUser(String name,User user) {
//        transactionRepository.deleteTransactionsByCompanyNameAndUserId(name, user.getId());
//    }
//    public BigDecimal getTotalAmountForCompany(UUID companyId) {
//        return transactionRepository.getTotalAmountForCompany(companyId);
//    }
//
//    public List<Transaction> findAllByCompanyAndStatus(UUID companyId, Status status) {
//        return transactionRepository.findAllByCompanyAndStatus(companyId, status);
//    }
//    public List<Transaction> findTransactionsByClientUsername(String clientUsername) {
//        return transactionRepository.findTransactionsByClientUsername(clientUsername);
//    }
//
//    public List<Transaction> findTransactionsByCompanyAndClientUsername(UUID companyId, String clientUsername) {
//        return transactionRepository.findTransactionsByCompanyAndClientUsername(companyId, clientUsername);
//    }
//
//    public List<Transaction> findTransactionsByCurrencyAndAmountGreaterThan(String currency, BigDecimal amount) {
//        return transactionRepository.findTransactionsByCurrencyAndAmountGreaterThan(currency, amount);
//    }

}
