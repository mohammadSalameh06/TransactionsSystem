package com.example.transactionValidation.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    @Column(name = "balance")
    private Double balance;

    @OneToMany(mappedBy = "sourceAccount", fetch = FetchType.LAZY)
    private List<Transaction> sourceTransactions;

    @OneToMany(mappedBy = "destinationAccount", fetch = FetchType.LAZY)
    private List<Transaction> destinationTransactions;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
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

    public List<Transaction> getSourceTransactions() {
        return sourceTransactions;
    }

    public void setSourceTransactions(List<Transaction> sourceTransactions) {
        this.sourceTransactions = sourceTransactions;
    }

    public List<Transaction> getDestinationTransactions() {
        return destinationTransactions;
    }

    public void setDestinationTransactions(List<Transaction> destinationTransactions) {
        this.destinationTransactions = destinationTransactions;
    }
}
