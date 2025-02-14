package com.rabobank.lostandfoundapi.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class ClaimedItemDTO {
    @Schema(description = "Name of the lost item")
    String lostItem;
    @JsonProperty("claimedBy")
    List<UserDTO> users;
}
