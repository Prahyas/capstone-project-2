package com.techelevator.tenmo.services;

import org.springframework.web.client.RestTemplate;

public class TransferService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public TransferService(String url) {
        this.baseUrl = url;
    }

    //TODO
    public void getTransferHistory() {

    }

    //TODO
    public void getPendingRequests() {

    }

    //TODO
    public void sendBucks() {

    }

    //TODO
    public void requestBucks() {

    }
}
