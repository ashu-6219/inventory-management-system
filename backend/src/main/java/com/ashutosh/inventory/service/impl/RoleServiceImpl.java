package com.ashutosh.inventory.service.impl;

import com.ashutosh.inventory.constants.MessageConstants;
import com.ashutosh.inventory.dto.role.RoleRequest;
import com.ashutosh.inventory.dto.role.RoleResponse;
import com.ashutosh.inventory.entity.Role;
import com.ashutosh.inventory.exception.ResourceAlreadyExistsException;
import com.ashutosh.inventory.exception.ResourceNotFoundException;
import com.ashutosh.inventory.mapper.RoleMapper;
import com.ashutosh.inventory.repository.RoleRepository;
import com.ashutosh.inventory.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponse createRole(RoleRequest request) {

        if (roleRepository.existsByRoleName(request.getRoleName())) {
            throw new ResourceAlreadyExistsException(
                    MessageConstants.ROLE_ALREADY_EXISTS
                            + request.getRoleName());
        }

        Role role = roleMapper.toEntity(request);

        Role savedRole = roleRepository.save(role);

        return roleMapper.toResponse(savedRole);
    }

    @Override
    public List<RoleResponse> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toResponse)
                .toList();
    }

    @Override
    public RoleResponse getRoleById(Long roleId) {

        Role role = findRoleById(roleId);

        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse updateRole(Long roleId,
                                   RoleRequest request) {

        Role role = findRoleById(roleId);

        if (!role.getRoleName().equals(request.getRoleName())
                && roleRepository.existsByRoleName(request.getRoleName())) {

            throw new ResourceAlreadyExistsException(
                    MessageConstants.ROLE_ALREADY_EXISTS
                            + request.getRoleName());
        }

        role.setRoleName(request.getRoleName());
        role.setDescription(request.getDescription());

        Role updatedRole = roleRepository.save(role);

        return roleMapper.toResponse(updatedRole);
    }

    @Override
    public void deleteRole(Long roleId) {

        Role role = findRoleById(roleId);

        roleRepository.delete(role);
    }

    private Role findRoleById(Long roleId) {

        return roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.ROLE_NOT_FOUND
                                        + roleId));
    }
}