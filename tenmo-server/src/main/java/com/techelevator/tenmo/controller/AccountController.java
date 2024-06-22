package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    // Get all accounts
    @RequestMapping(path = "/all/account", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public List<Account> list() {
        return accountDao.getAccounts();
    }

    @RequestMapping(path = "/{id}/account", method = RequestMethod.GET)
    public Account get(@PathVariable int id) {
        Account account = accountDao.getAccountByUserId(id);
        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        } else {
            return account;
        }
    }

    // Get one account by account id
    @RequestMapping(path = "/{id}/account/{accountId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public Account getAccountByAccountId(@PathVariable int id, @PathVariable int accountId) {
        try {
            return accountDao.getAccountObjByAccountId(id, accountId);
        } catch(DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

    // Update balance
    @RequestMapping(path = "/{id}/account/{accountId}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER')")
    public Account updateAccountAddBalance(@PathVariable int id, @PathVariable int accountId, @RequestBody Account account) {
        account.setAccount_id(accountId);
        try {
            return accountDao.updateAccountBalance(id, accountId, account);
        } catch(DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

}
