package com.rabobank.lostandfoundapi.service;

import com.rabobank.lostandfoundapi.entity.Claim;

import java.util.List;

public interface ClaimsService {

    /**
     * Stores the claimed item in the claims table along with the associated user
     * @param claim
     */
    void claimItem(Claim claim);

    /**
     * Returns a list of all the claimed items and associated users
     * @return
     */
    List<Claim> getClaimedItems();
}
