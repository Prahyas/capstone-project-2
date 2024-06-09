package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferDao transferDao;

    public TransferController(TransferDao transferDao) {

        this.transferDao = transferDao;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Transfer[]> getTransferHistory(@PathVariable int accountId) {
        Transfer[] transfers = transferDao.getTransferHistoryByAccountId(accountId).toArray(new Transfer[0]);
        return ResponseEntity.ok(transfers);
    }

    @GetMapping("/pending/{accountId}")
    public ResponseEntity<Transfer[]> getPendingRequests(@PathVariable int accountId) {
        Transfer[] pendingRequests = transferDao.getTransferHistoryInPendingByAccountId(accountId).toArray(new Transfer[0]);
        return ResponseEntity.ok(pendingRequests);
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendBucks(@RequestBody Transfer transfer) {
        //transferDao.sendBucks(transfer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/request")
    public ResponseEntity<Void> requestBucks(@RequestBody Transfer transfer) {
        //transferDao.requestBucks(transfer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}