package com.rabobank.lostandfoundapi.bo;

import com.rabobank.lostandfoundapi.controller.request.ClaimRequest;
import com.rabobank.lostandfoundapi.controller.response.ClaimedItemsResponse;

public interface ClaimsBO {

    /**
     * Allows a user to claim an existing lost item
     * If an item is claimed successfully, then the requested quantity is deducted from the current number of items.
     * If the current number is 0, then the item is deleted from the database.
     * Throws IllegalArgumentException if the number of requested items exceeds the number of items in the database.
     * @param claimRequest
     */
    void claimLostItem(ClaimRequest claimRequest);

    /**
     * Returns a list of items and associated users
     * @return
     */
    ClaimedItemsResponse getClaimedItems();
}
