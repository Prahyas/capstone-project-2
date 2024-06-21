package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.util.List;

public interface AccountDao {

    List<Account> getAccounts();

    Account getAccountByUserId(int userId);

    Account getAccountObjByAccountId(int userId, int accountId);

    //transferId to get amount, accountId to get balance, Account to update user's account
    Account updateAccountSubtractBalance(int userId, int transferId, int accountId);

    //transferId to get amount, accountId to get balance, Account to update user's account
    Account updateAccountAddBalance(int userId, int transferId, int accountId);
}
