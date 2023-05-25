package com.example.dicegame;

import java.io.Serializable;

public class Account implements Serializable {
    private String accountNumber;
    private int balance;
    private String bankName;

    public Account(String accountNumber, int balance, String bankName) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(int withdrawAmount) {
        this.balance-=withdrawAmount;
    }

    public void deposit(int depositAmount){
        this.balance+=depositAmount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
