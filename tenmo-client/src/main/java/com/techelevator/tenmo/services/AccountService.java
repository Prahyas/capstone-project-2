package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountService {

    private final String BASE_URL = "http://localhost:8080/user/";
    private final RestTemplate restTemplate = new RestTemplate();

    //TODO
    public BigDecimal getBalance(AuthenticatedUser currentUser) {
        BigDecimal balance = null;
        try {
            balance = restTemplate.getForObject(BASE_URL + "balance", BigDecimal.class, currentUser.getToken());
        } catch (RestClientException e) {
            System.out.println("Error retrieving balance: " + e.getMessage());
        }
        return balance;
    }
}
