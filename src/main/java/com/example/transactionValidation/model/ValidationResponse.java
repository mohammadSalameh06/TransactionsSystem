package com.example.transactionValidation.model;

public class ValidationResponse {

    private String transactionId;
    private boolean isValid;
    private String errorMessage;

    public ValidationResponse(String transactionId, boolean isValid, String errorMessage) {
        this.transactionId = transactionId;
        this.isValid = isValid;
        this.errorMessage = errorMessage;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
