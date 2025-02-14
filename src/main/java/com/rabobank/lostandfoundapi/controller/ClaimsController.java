package com.rabobank.lostandfoundapi.controller;

import com.rabobank.lostandfoundapi.bo.ClaimsBO;
import com.rabobank.lostandfoundapi.common.ErrorResponse;
import com.rabobank.lostandfoundapi.controller.request.ClaimRequest;
import com.rabobank.lostandfoundapi.controller.response.ClaimedItemsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class ClaimsController {

    private final ClaimsBO claimsBO;

    @Operation(summary = "Claim lost item(s)", description = "Claim one or more lost item of the same type.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping(value = "/claim",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> claimLostItem(@RequestBody @Valid ClaimRequest claimRequest) {
        claimsBO.claimLostItem(claimRequest);
        return ResponseEntity.ok("Item(s) claimed successfully");
    }

    @Operation(summary = "Get all claimed items", description = "Get a list of all claimed items and associated users.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = ClaimedItemsResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping
    public ResponseEntity<ClaimedItemsResponse> getClaimedItems() {
        ClaimedItemsResponse claimedItemsResponse = claimsBO.getClaimedItems();
        return ResponseEntity.ok(claimedItemsResponse);
    }
}
