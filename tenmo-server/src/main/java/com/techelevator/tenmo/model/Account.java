package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Account {

    private int account_id;
    private int user_id;
    private BigDecimal balance;

    public Account() {}

    public Account(int account_id, int user_id, BigDecimal balance) {
        this.account_id = account_id;
        this.user_id = user_id;
        this.balance = balance;
    }

    @NotNull
    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(@NotNull int account_id) {
        this.account_id = account_id;
    }

    @NotNull
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(@NotNull int user_id) {
        this.user_id = user_id;
    }

    public @NotNull BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(@NotNull BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", user_id=" + user_id +
                ", balance=" + balance +
                '}';
    }
}
