package com.rabobank.lostandfoundapi.controller.response;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class ClaimedItemsResponse {
    List<ClaimedItemDTO> claimedItems;
}
