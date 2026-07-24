package com.ashutosh.inventory.dto.user;

import com.ashutosh.inventory.constants.ValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @NotBlank(message = ValidationMessages.USER_FULL_NAME_REQUIRED)
    @Size(max = 150, message = ValidationMessages.USER_FULL_NAME_SIZE)
    private String fullName;

    @NotBlank(message = ValidationMessages.USERNAME_REQUIRED)
    @Size(max = 50, message = ValidationMessages.USERNAME_SIZE)
    private String username;

    @Email(message = ValidationMessages.EMAIL_INVALID)
    @Size(max = 150, message = ValidationMessages.EMAIL_SIZE)
    private String email;

    @NotBlank(message = ValidationMessages.PASSWORD_REQUIRED)
    @Size(max = 255, message = ValidationMessages.PASSWORD_SIZE)
    private String password;

    @Size(max = 20, message = ValidationMessages.PHONE_SIZE)
    private String phone;

    private Boolean isActive;
}