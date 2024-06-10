package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.RegisterUserDto;

import java.util.List;

public interface AccountDao {

    List<Account> getAccounts();

    Account getAccountByUserId(int userId);

    int getAccountIdByUserId(int userId);
}
