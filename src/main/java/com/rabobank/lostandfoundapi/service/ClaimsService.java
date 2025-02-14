package com.rabobank.lostandfoundapi.service;

import com.rabobank.lostandfoundapi.entity.Claim;

import java.util.List;

public interface ClaimsService {

    void claimItem(Claim claim);

    List<Claim> getClaimedItems();
}
