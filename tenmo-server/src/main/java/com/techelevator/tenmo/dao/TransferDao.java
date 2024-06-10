package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    List<Transfer> getAllTransfers();

    List<Transfer> getTransferHistoryByUserId(int userId);

    List<Transfer> getTransferHistoryInPendingByUserId(int userId);

    //TODO
    // Need Send TE bucks

    //TODO
    // Need Request TE bucks
}
