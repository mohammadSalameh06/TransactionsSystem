package com.example.transactionValidation.DTO;

import com.example.transactionValidation.model.AccountStatus;
import com.example.transactionValidation.model.AccountType;

public class AccountDTO {
    private Long accountId;
    private String accountHolderName;
    private Double balance;



    private AccountType accountType;
    private AccountStatus accountStatus;


    public AccountDTO(Long accountId, String accountHolderName, AccountStatus accountStatus ,AccountType accountType, Double balance) {
        this.accountId = accountId;
        this.accountHolderName = accountHolderName;
        this.accountStatus=accountStatus;
        this.accountType=accountType;
        this.balance = balance;


    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
