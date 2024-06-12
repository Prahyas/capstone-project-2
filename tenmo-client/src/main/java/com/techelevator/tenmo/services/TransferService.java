package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class TransferService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ConsoleService consoleService = new ConsoleService();

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

    public Transfer getPendingTransferByTransferId(AuthenticatedUser currentUser, int transferId) {
        Transfer transfer = null;
        try {
            transfer = restTemplate.getForObject(baseUrl + "user/{id}/transfer/{transferId}", Transfer.class, currentUser.getUser().getId(), transferId);
        } catch (RestClientException e) {
            System.out.println("Error retrieving pending transfer record: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No transfer record found!");
        }
        return transfer;
    }

    public boolean approveRequest(AuthenticatedUser currentUser) {
        int transferId = consoleService.promptForInt("Please select a transfer ID to approve: ");
        Transfer transfer = getPendingTransferByTransferId(currentUser, transferId);
        transfer.setTransferStatusId(2);
        int receiverAccountTo = transfer.getAccountTo();
        HttpEntity<Transfer> transferEntity = makeEntity(transfer);
        boolean success = false;
        try {
            restTemplate.put(baseUrl + "user/{id}/transfer/{transferId}", transferEntity, currentUser.getUser().getId(), transferId);
            success = true;
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return success;
    }

    public void rejectRequest(AuthenticatedUser currentUser) {
        int transferId = consoleService.promptForInt("Please select a transfer ID to reject: ");
        Transfer transfer = getPendingTransferByTransferId(currentUser, transferId);
        transfer.setTransferStatusId(3);
        HttpEntity<Transfer> entity = makeEntity(transfer);
        try {
            restTemplate.put(baseUrl + "user/{id}/transfer/{transferId}", entity, currentUser.getUser().getId(), transferId);
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
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

    private HttpEntity<Transfer> makeEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(transfer, headers);
    }
}
