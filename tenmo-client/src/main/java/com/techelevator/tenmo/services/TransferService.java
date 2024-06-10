package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class TransferService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public TransferService(String url) {
        this.baseUrl = url;
    }

    public void getTransferHistory(AuthenticatedUser currentUser) {
        Transfer[] transferHistoryArray = null;
        try {
            transferHistoryArray = restTemplate.getForObject(baseUrl + "user/{id}/transfer", Transfer[].class, currentUser.getUser().getId());
            System.out.println("-------------------------------------");
            System.out.println("           Transfer History          ");
            System.out.println("-------------------------------------");
            for (Transfer transfer : transferHistoryArray) {
                System.out.println(transfer);
            }
        } catch (RestClientException e) {
            System.out.println("Error retrieving transfer record: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No transfer record found!");
        }
    }

    public void getPendingRequests(AuthenticatedUser currentUser) {
        Transfer[] transferHistoryPendingArray = null;
        try {
            transferHistoryPendingArray = restTemplate.getForObject(baseUrl + "user/{id}/transfer/pending/", Transfer[].class, currentUser.getUser().getId());
            System.out.println("-------------------------------------");
            System.out.println("    Transfer-In-Pending Details      ");
            System.out.println("-------------------------------------");
            for (Transfer transfer : transferHistoryPendingArray) {
                System.out.println(transfer);
            }
        } catch (RestClientException e) {
            System.out.println("Error retrieving pending transfer record: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No transfer record found!");
        }
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
