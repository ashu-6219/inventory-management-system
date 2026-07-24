package com.ashutosh.inventory.controller;

import com.ashutosh.inventory.constants.ApiPaths;
import com.ashutosh.inventory.dto.ApiResponse;
import com.ashutosh.inventory.dto.role.RoleRequest;
import com.ashutosh.inventory.dto.role.RoleResponse;
import com.ashutosh.inventory.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ashutosh.inventory.constants.MessageConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.ROLE)
@RequiredArgsConstructor
@Tag(name = "Role Management", description = "APIs for managing roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    @Operation(summary = "Create a Role")
    public ResponseEntity<ApiResponse<RoleResponse>>
    createRole(@Valid @RequestBody RoleRequest request) {

        RoleResponse response = roleService.createRole(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<RoleResponse>builder()
                        .success(true)
                        .message(MessageConstants.ROLE_CREATED)
                        .data(response)
                        .build());
    }

    @GetMapping
    @Operation(summary = "Get All Roles")
    public ResponseEntity<ApiResponse<List<RoleResponse>>>
    getAllRoles() {

        List<RoleResponse> response =
                roleService.getAllRoles();

        return ResponseEntity.ok(
                ApiResponse.<List<RoleResponse>>builder()
                        .success(true)
                        .message(MessageConstants.ROLES_RETRIEVED)
                        .data(response)
                        .build());
    }

    @GetMapping("/{roleId}")
    @Operation(summary = "Get Role By ID")
    public ResponseEntity<ApiResponse<RoleResponse>>
    getRoleById(@PathVariable Long roleId) {

        RoleResponse response =
                roleService.getRoleById(roleId);

        return ResponseEntity.ok(
                ApiResponse.<RoleResponse>builder()
                        .success(true)
                        .message(MessageConstants.ROLE_RETRIEVED)
                        .data(response)
                        .build());
    }

    @PutMapping("/{roleId}")
    @Operation(summary = "Update Role")
    public ResponseEntity<ApiResponse<RoleResponse>>
    updateRole(@PathVariable Long roleId,
               @Valid @RequestBody RoleRequest request) {

        RoleResponse response =
                roleService.updateRole(roleId, request);

        return ResponseEntity.ok(
                ApiResponse.<RoleResponse>builder()
                        .success(true)
                        .message(MessageConstants.ROLE_UPDATED)
                        .data(response)
                        .build());
    }

    @DeleteMapping("/{roleId}")
    @Operation(summary = "Delete Role")
    public ResponseEntity<ApiResponse<Void>>
    deleteRole(@PathVariable Long roleId) {

        roleService.deleteRole(roleId);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message(MessageConstants.ROLE_DELETED)
                        .build());
    }
}