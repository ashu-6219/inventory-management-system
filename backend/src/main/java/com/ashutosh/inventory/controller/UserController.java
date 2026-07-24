package com.ashutosh.inventory.controller;

import com.ashutosh.inventory.constants.ApiPaths;
import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.ApiResponse;
import com.ashutosh.inventory.dto.user.UserRequest;
import com.ashutosh.inventory.dto.user.UserResponse;
import com.ashutosh.inventory.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.USER)
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create a User")
    public ResponseEntity<ApiResponse<UserResponse>> createUser(
            @Valid @RequestBody UserRequest request) {

        UserResponse response = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<UserResponse>builder()
                        .success(true)
                        .message(MessageConstants.USER_CREATED)
                        .data(response)
                        .build());
    }

    @GetMapping
    @Operation(summary = "Get All Users")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {

        List<UserResponse> response = userService.getAllUsers();

        return ResponseEntity.ok(
                ApiResponse.<List<UserResponse>>builder()
                        .success(true)
                        .message(MessageConstants.USERS_FETCHED)
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get User By ID")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(
            @PathVariable Long id) {

        UserResponse response = userService.getUserById(id);

        return ResponseEntity.ok(
                ApiResponse.<UserResponse>builder()
                        .success(true)
                        .message(MessageConstants.USER_FETCHED)
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update User")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {

        UserResponse response = userService.updateUser(id, request);

        return ResponseEntity.ok(
                ApiResponse.<UserResponse>builder()
                        .success(true)
                        .message(MessageConstants.USER_UPDATED)
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete User")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message(MessageConstants.USER_DELETED)
                        .build()
        );
    }
}