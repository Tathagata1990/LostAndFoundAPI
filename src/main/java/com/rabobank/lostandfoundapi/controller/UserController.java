package com.rabobank.lostandfoundapi.controller;

import com.rabobank.lostandfoundapi.bo.UserBOImpl;
import com.rabobank.lostandfoundapi.common.ErrorResponse;
import com.rabobank.lostandfoundapi.controller.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserBOImpl userBO;

    @Operation(summary = "Get user details", description = "Get the details of an user by user id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(
            @Parameter(description = "The user id")
            @PathVariable
            Long userId) {
        return ResponseEntity.ok(userBO.getUser(userId));
    }
}
