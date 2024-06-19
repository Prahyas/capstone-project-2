package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.util.BasicLogger;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public AccountService(String url) {
        this.baseUrl = url;
    }

    public BigDecimal getBalance(AuthenticatedUser currentUser) {
        BigDecimal balance = null;
        Account account = null;
        try {
            account = restTemplate.getForObject(baseUrl + "user/{id}/account", Account.class, currentUser.getUser().getId());
            balance = account.getBalance();
        } catch (RestClientException e) {
            BasicLogger.log("Error retrieving balance: " + e.getMessage());
        }
        return balance;
    }
}
