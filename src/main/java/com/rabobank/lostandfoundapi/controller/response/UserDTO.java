package com.rabobank.lostandfoundapi.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserDTO {
    @Schema(description = "Id of the user")
    Long id;
    @Schema(description = "Name of the user")
    String name;
}
