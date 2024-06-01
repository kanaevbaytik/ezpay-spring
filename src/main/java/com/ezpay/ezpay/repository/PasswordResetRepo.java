package com.ezpay.ezpay.repository;

import com.ezpay.ezpay.domains.entity.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetRepo extends JpaRepository<PasswordReset, Long> {
    Optional<PasswordReset> findByCode(String code);
}
