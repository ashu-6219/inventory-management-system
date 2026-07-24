package com.ashutosh.inventory.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.ashutosh.inventory.constants.ValidationMessages;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRequest {

    @NotBlank(message = ValidationMessages.ROLE_NAME_REQUIRED)
    @Size(max = 50, message = ValidationMessages.ROLE_NAME_SIZE)
    private String roleName;

    @Size(max = 200, message = ValidationMessages.ROLE_DESCRIPTION_SIZE)
    private String description;
}