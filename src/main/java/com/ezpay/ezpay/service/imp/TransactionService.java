package com.ezpay.ezpay.service.imp;

import com.ezpay.ezpay.domains.dto.request.TransactionDtoRequest;
import com.ezpay.ezpay.domains.dto.response.TransactionDto;
import com.ezpay.ezpay.domains.entity.Company;
import com.ezpay.ezpay.domains.entity.Transaction;
import com.ezpay.ezpay.domains.entity.User;
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
import java.util.stream.Collectors;

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

    public TransactionDto createTransaction(TransactionDtoRequest transaction, UUID companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow();
        Transaction savedTransaction = transactionRepository.save(Transaction.builder()
                .company(company)
                .amount(transaction.getAmount())
                .cardNumber(transaction.getCardNumber())
                .clientUsername(transaction.getClientUsername())
                .status(Status.OK)
                .currency(transaction.getCurrency())
                .operations(Operations.Payment)
                .build());
        return new TransactionDto(
                savedTransaction.getId(), savedTransaction.getOperations().toString(),
                savedTransaction.getAmount(), savedTransaction.getClientUsername(), savedTransaction.getStatus().toString()
        );
    }

    public BigDecimal getTotalAmountForCompany(UUID companyId) {
        return transactionRepository.getTotalAmountForCompany(companyId);
    }
    public BigDecimal getTotalAmount() {
        return transactionRepository.getTotalAmount();
    }

    public List<Transaction> getAllForUser(User user) {
        List<Transaction> allTransactions = transactionRepository.findAll();
        List<Transaction> userTransactions = allTransactions.stream()
                .filter(transaction -> transaction.getCompany().getUser().equals(user))
                .collect(Collectors.toList());
        return userTransactions;
    }
    public BigDecimal getTotalAmountForUser(User user) {
        List<Transaction> allTransactions = transactionRepository.findAll();
        BigDecimal totalAmount = allTransactions.stream()
                .filter(transaction -> transaction.getCompany().getUser().equals(user))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalAmount;
    }



}
