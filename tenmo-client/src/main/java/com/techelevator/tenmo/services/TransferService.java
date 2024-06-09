package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TransferService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public TransferService(String url) {

        this.baseUrl = url;
    }

    //TODO
    public void getTransferHistory(int accountId) {
        String url = baseUrl + "/transfers/" + accountId;
        ResponseEntity<Transfer[]> responseEntity = restTemplate.getForEntity(url, Transfer[].class);
        //return responseEntity.getBody();
    }

    //TODO
    public void getPendingRequests(int accountId) {
        String url = baseUrl + "/transfers/pending/" + accountId;
        ResponseEntity<Transfer[]> responseEntity = restTemplate.getForEntity(url, Transfer[].class);
        //return responseEntity.getBody();
    }

    //TODO
    public void sendBucks(Transfer transfer) {
        String url = baseUrl + "/transfers/send";
        restTemplate.postForObject(url, transfer, Void.class);
    }

    //TODO
    public void requestBucks(Transfer transfer) {
        String url = baseUrl + "/transfers/request";
        restTemplate.postForObject(url, transfer, Void.class);
    }
}
