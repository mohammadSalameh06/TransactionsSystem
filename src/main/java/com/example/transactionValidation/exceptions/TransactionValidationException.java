package com.example.transactionValidation.exceptions;

import java.util.List;

public class TransactionValidationException extends RuntimeException {
    private final List<String> errors;

    public TransactionValidationException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
