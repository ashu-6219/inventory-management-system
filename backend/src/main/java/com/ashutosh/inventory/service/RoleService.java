package com.ashutosh.inventory.service;

import com.ashutosh.inventory.dto.role.RoleRequest;
import com.ashutosh.inventory.dto.role.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse createRole(RoleRequest request);

    List<RoleResponse> getAllRoles();

    RoleResponse getRoleById(Long roleId);

    RoleResponse updateRole(Long roleId,
                            RoleRequest request);

    void deleteRole(Long roleId);
}