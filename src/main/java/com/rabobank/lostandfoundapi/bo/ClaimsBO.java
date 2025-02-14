package com.rabobank.lostandfoundapi.bo;

import com.rabobank.lostandfoundapi.controller.request.ClaimRequest;
import com.rabobank.lostandfoundapi.controller.response.ClaimedItemsResponse;

public interface ClaimsBO {

    void claimLostItem(ClaimRequest claimRequest);

    ClaimedItemsResponse getClaimedItems();
}
