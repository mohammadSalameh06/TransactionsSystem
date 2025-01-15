package com.example.transactionValidation.service;


import com.example.transactionValidation.DTO.AccountDTO;
import com.example.transactionValidation.model.Account;
import com.example.transactionValidation.repositery.AccountRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public void createAccount(Account account) {
        accountRepo.save(account);
    }

    public AccountDTO getAccount(Long accountId) {
        Optional<Account> account = accountRepo.findById(accountId);
        if (account.isPresent()) {
            Account acc = account.get();
            return new AccountDTO(acc.getAccountId(), acc.getAccountHolderName(), acc.getAccountStatus(), acc.getAccountType(), acc.getBalance());
        }
        return null;
    }

    public void updateAccount(Long accountId, Account account) {
        if (accountRepo.existsById(accountId)) {
            account.setAccountId(accountId);
            accountRepo.save(account);
        }
    }

    public void deleteAccount(Long accountId) {
        if (accountRepo.existsById(accountId)) {
            accountRepo.deleteById(accountId);
        }
    }
}
