package com.rabobank.lostandfoundapi.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ClaimDTO {
    @Schema(description = "Name of the requested item")
    String lostItemName;
    @Schema(description = "Quantity of the requested item")
    @NotNull(message = "Quantity of the requested item cannot be null")
    @Positive(message = "Quantity of the requested item cannot be zero or a negative number")
    Integer lostItemQuantity;
    @Schema(description = "Place where the requested item was found")
    String lostItemPlace;
}
