package com.rabobank.lostandfoundapi.controller;

import com.rabobank.lostandfoundapi.bo.LostItemsBO;
import com.rabobank.lostandfoundapi.common.ErrorResponse;
import com.rabobank.lostandfoundapi.controller.response.LostItemsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/lost-items")
@RequiredArgsConstructor
public class LostItemsController {

    private final LostItemsBO lostItemsBO;

    @Operation(summary = "Upload document", description = "Upload a PDF file containing lost items.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "File uploaded successfully",
                    content = @Content(schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping(path = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> uploadDocument(
            @Parameter(description = "PDF file containing lost items")
            @RequestParam("file") MultipartFile file) {
        lostItemsBO.processDocument(file);
        return ResponseEntity.ok("Document containing lost items processed successfully");
    }

    @Operation(summary = "Get all lost items", description = "Get a list of all lost items.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = LostItemsResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping
    public ResponseEntity<LostItemsResponse> getLostItems() {
        return ResponseEntity.ok(lostItemsBO.getLostItems());
    }
}
