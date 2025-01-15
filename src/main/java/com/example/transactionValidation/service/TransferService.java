package com.example.transactionValidation.service;

import com.example.transactionValidation.exceptions.TransactionValidationException;
import com.example.transactionValidation.model.Account;
import com.example.transactionValidation.model.Transaction;
import com.example.transactionValidation.model.Transfer;
import com.example.transactionValidation.repositery.AccountRepo;
import com.example.transactionValidation.repositery.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransferService {

    @Autowired
    private AccountRepo accountRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private TransactionsService transactionsService;

    public boolean validateTransaction(Long sourceAccountId, Long destinationAccountId, double amount) {
        Optional<Account> sourceAccountOpt = accountRepository.findById(sourceAccountId);
        Optional<Account> destinationAccountOpt = accountRepository.findById(destinationAccountId);

        if (sourceAccountOpt.isEmpty() || destinationAccountOpt.isEmpty()) {
            return false;
        }

        Account sourceAccount = sourceAccountOpt.get();
        if (sourceAccount.getBalance() < amount) {
            return false;
        }

        return true;
    }
    public Double getSourceAccountBalance(Long sourceAccountId){
        Optional<Account> sourceAccountOpt= accountRepository.findById(sourceAccountId);
        if (sourceAccountOpt.isPresent()){
            return sourceAccountOpt.get().getBalance();
        }
        throw new IllegalArgumentException("The Source Account is not found");

    }

    @Transactional
    public Transfer transferFunds(Long sourceAccountId, Long destinationAccountId, double amount, String currency, String timestamp) {
        Transaction transaction = new Transaction();
        transaction.setSourceAccount(accountRepository.findById(sourceAccountId).orElseThrow(() -> new IllegalArgumentException("Source account not found")));
        transaction.setDestinationAccount(accountRepository.findById(destinationAccountId).orElseThrow(() -> new IllegalArgumentException("Destination account not found")));
        transaction.setAmount(amount);
        transaction.setCurrency(currency);
        transaction.setTimestamp(timestamp);

        try {
            transactionsService.validate(transaction);
        } catch (TransactionValidationException e) {
            throw new IllegalArgumentException("Transaction validation failed: " + String.join(", ", e.getErrors()));
        }

        if (!validateTransaction(sourceAccountId, destinationAccountId, amount)) {
            throw new IllegalArgumentException("Invalid transaction or insufficient funds");
        }

        Account sourceAccount = accountRepository.findById(sourceAccountId).get();
        Account destinationAccount = accountRepository.findById(destinationAccountId).get();

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        Transfer transfer = new Transfer();
        transfer.setSourceAccountId(sourceAccount.getAccountId());
        transfer.setDestinationAccountId(destinationAccount.getAccountId());
        transfer.setAmount(amount);
        transfer.setCurrency(currency);
        transfer.setTimestamp(LocalDateTime.now());

        return transferRepository.save(transfer);
    }


}
