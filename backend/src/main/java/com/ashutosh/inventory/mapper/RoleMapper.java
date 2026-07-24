package com.ashutosh.inventory.mapper;

import com.ashutosh.inventory.dto.role.RoleRequest;
import com.ashutosh.inventory.dto.role.RoleResponse;
import com.ashutosh.inventory.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role toEntity(RoleRequest request) {

        return Role.builder()
                .roleName(request.getRoleName())
                .description(request.getDescription())
                .build();
    }

    public RoleResponse toResponse(Role role) {

        return RoleResponse.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .build();
    }
}