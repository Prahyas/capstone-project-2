package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    List<Transfer> getTransferHistoryByAccountId(int accountId);
    List<Transfer> getTransferHistoryInPendingByAccountId(int accountId);

    // Need Send TE bukcs
    // Need Request TE bucks
}
