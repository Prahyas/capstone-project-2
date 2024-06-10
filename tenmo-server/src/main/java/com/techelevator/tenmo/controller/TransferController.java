package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class TransferController {

    private final TransferDao transferDao;

    public TransferController(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    // Get the entire transfer history
    @RequestMapping(path = "/all/transfer", method = RequestMethod.GET)
    public List<Transfer> getAllTransferList() {
        return transferDao.getAllTransfers();
    }

    // Get the transfer history of the current user
    @RequestMapping(path = "/{id}/transfer", method = RequestMethod.GET)
    public List<Transfer> getTransferHistory(@PathVariable int id) {
        return transferDao.getTransferHistoryByUserId(id);
    }

    // Get the pending transfer history of the current user
    @RequestMapping(path = "/{id}/transfer/pending", method = RequestMethod.GET)
    public List<Transfer> getPendingRequests(@PathVariable int id) {
        return transferDao.getTransferHistoryInPendingByUserId(id);
    }

    //TODO
    @PostMapping("/user/{id}/transfer/sending}")
    public ResponseEntity<Void> sendBucks(@RequestBody Transfer transfer) {
        //transferDao.sendBucks(transfer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //TODO
    @PostMapping("/user/{id}/transfer/requesting")
    public ResponseEntity<Void> requestBucks(@RequestBody Transfer transfer) {
        //transferDao.requestBucks(transfer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}