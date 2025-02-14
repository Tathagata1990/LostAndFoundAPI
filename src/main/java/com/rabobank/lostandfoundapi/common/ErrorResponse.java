package com.rabobank.lostandfoundapi.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    @Schema(description = "The error code")
    Integer code;
    @Schema(description = "The error message")
    String message;
}
