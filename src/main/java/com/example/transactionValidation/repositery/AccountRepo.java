package com.example.transactionValidation.repositery;

import com.example.transactionValidation.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
    boolean existsByAccountId(Long accountId);
}
