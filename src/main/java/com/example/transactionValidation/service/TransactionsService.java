package com.example.transactionValidation.service;

import com.example.transactionValidation.exceptions.TransactionValidationException;
import com.example.transactionValidation.model.Account;
import com.example.transactionValidation.model.AccountStatus;

import com.example.transactionValidation.model.Transaction;

import com.example.transactionValidation.model.ValidationResponse;
import com.example.transactionValidation.repositery.AccountRepo;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionsService {

    private final AccountRepo accountRepository;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public TransactionsService(AccountRepo accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ValidationResponse validate(Transaction transaction) {
        List<String> errors = new ArrayList<>();

        Account sourceAccount = accountRepository.findById(transaction.getSourceAccount().getAccountId())
                .orElse(null);
        if (sourceAccount == null) {
            errors.add("Source account ID not found.");
        } else if (sourceAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            errors.add("Source account is not active. Status: " + sourceAccount.getAccountStatus());
        }

        Account destinationAccount = accountRepository.findById(transaction.getDestinationAccount().getAccountId())
                .orElse(null);
        if (destinationAccount == null) {
            errors.add("Destination account ID not found.");
        } else if (destinationAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            errors.add("Destination account is not active. Status: " + destinationAccount.getAccountStatus());
        }

        if (transaction.getAmount() <= 0) {
            errors.add("Amount must be greater than zero.");
        }

        if (!List.of("USD", "EUR", "AED", "JOD").contains(transaction.getCurrency())) {
            errors.add("Currency not supported: " + transaction.getCurrency());
        }

        try {
            LocalDate.parse(transaction.getTimestamp(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            errors.add("Timestamp must be in the format yyyy-MM-dd'T'HH:mm:ss and a valid date.");
        }

        if (!errors.isEmpty()) {
            throw new TransactionValidationException("Transaction validation failed", errors);
        }

        return new ValidationResponse(transaction.getTransactionId(), true, null);
    }



    public static class ValidationErrorResponse {
        private String message;
        private List<String> errors;

        public ValidationErrorResponse(String message, List<String> errors) {
            this.message = message;
            this.errors = errors;
        }

        public String getMessage() {
            return message;
        }

        public List<String> getErrors() {
            return errors;
        }
    }

}
