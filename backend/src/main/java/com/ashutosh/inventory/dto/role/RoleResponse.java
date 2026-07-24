package com.ashutosh.inventory.dto.role;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponse {

    private Long roleId;

    private String roleName;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}