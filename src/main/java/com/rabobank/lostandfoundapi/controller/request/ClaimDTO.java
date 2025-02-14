package com.rabobank.lostandfoundapi.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ClaimDTO {
    @Schema(description = "Name of the requested item")
    String lostItemName;
    @Schema(description = "Quantity of the requested item")
    Integer lostItemQuantity;
    @Schema(description = "Place where the requested item was found")
    String lostItemPlace;
}
