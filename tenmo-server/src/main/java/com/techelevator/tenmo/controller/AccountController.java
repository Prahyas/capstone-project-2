package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user")
public class AccountController {

    private AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    //TODO
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Account> list() {
        return accountDao.getAccounts();
    }

    //TODO
    @RequestMapping(path = "/{id}/balance", method = RequestMethod.GET)
    public Account get(@PathVariable int id) {
        Account account = accountDao.getAccountByUserId(id);
        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        } else {
            return account;
        }
    }
}
