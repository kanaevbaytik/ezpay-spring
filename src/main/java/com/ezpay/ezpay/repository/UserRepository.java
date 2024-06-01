package com.ezpay.ezpay.repository;

import com.ezpay.ezpay.domains.entity.Transaction;
import com.ezpay.ezpay.domains.entity.User;
import org.eclipse.angus.mail.imap.protocol.BODY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

}
