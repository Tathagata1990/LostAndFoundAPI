package com.rabobank.lostandfoundapi.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ClaimRequest {
    @Schema(description = "Id of the user claiming the item")
    Long userId;
    ClaimDTO claimItem;
}
