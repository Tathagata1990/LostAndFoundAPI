package com.rabobank.lostandfoundapi.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LostItemDTO {
    @Schema(description = "Name of the lost item")
    String itemName;
    @Schema(description = "Quantity of the lost item")
    Integer quantity;
    @Schema(description = "Place where the item was found")
    String place;
}
