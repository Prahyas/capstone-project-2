package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    List<Transfer> getAllTransfers();

    List<Transfer> getTransferHistoryByUserId(int userId);

    List<Transfer> getTransferHistoryInPendingByUserId(int userId);

    // Need Send TE bukcs
    // Need Request TE bucks
}
