package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public AccountService(String url) {
        this.baseUrl = url;
    }

    public Account[] getAllAccounts(AuthenticatedUser currentUser) {
        Account[] accounts = null;
        try {
            ResponseEntity<Account[]> response =
                    restTemplate.exchange(baseUrl + "user/all/account", HttpMethod.GET, makeAuthEntity(currentUser), Account[].class);
            accounts = response.getBody();
        } catch (RestClientException e) {
            BasicLogger.log("Error retrieving accounts: " + e.getMessage());
        } catch (NullPointerException e) {
            BasicLogger.log("No transfer record found!");
        }
        return accounts;
    }

    public BigDecimal getBalance(AuthenticatedUser currentUser) {
        BigDecimal balance = null;
        try {
            ResponseEntity<Account> response =
                    restTemplate.exchange(baseUrl + "user/{id}/account", HttpMethod.GET, makeAuthEntity(currentUser), Account.class, currentUser.getUser().getId());
            balance = Objects.requireNonNull(response.getBody()).getBalance();
        } catch (RestClientException e) {
            BasicLogger.log("Error retrieving balance: " + e.getMessage());
        }
        return balance;
    }

    public Account getAccountByUserId(AuthenticatedUser currentUser) {
        Account account = null;
        try {
            ResponseEntity<Account> response = restTemplate.exchange(baseUrl + "user/{id}/account", HttpMethod.GET, makeAuthEntity(currentUser), Account.class, currentUser.getUser().getId());
            account = response.getBody();
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return account;
    }

    public void updateAccountSendBucks(Account updatedAccount, AuthenticatedUser currentUser, int targetAccountId) {
        HttpEntity<Account> entity = makeAccountEntity(updatedAccount, currentUser);

        try {
            restTemplate.exchange(baseUrl + "user/{id}/account/{accountId}", HttpMethod.PUT,
                    entity, Account.class, currentUser.getUser().getId(), targetAccountId);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
    }

    /*
    public void updateAccountRequestBucks(Account updatedAccount, AuthenticatedUser currentUser, int targetId) {
        HttpEntity<Account> entity = makeAccountEntity(updatedAccount, currentUser);

        try {
            restTemplate.exchange(baseUrl + "user/{id}/account/{accountId}/transfer/{transferId}/send/{targetId}", HttpMethod.PUT,
                    entity, Account.class, currentUser.getUser().getId(), targetId);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
    }
     */

    private HttpEntity<Account> makeAccountEntity(Account account, AuthenticatedUser currentUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(account, headers);
    }

    private HttpEntity<Account> makeAuthEntity(AuthenticatedUser currentUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }
}
