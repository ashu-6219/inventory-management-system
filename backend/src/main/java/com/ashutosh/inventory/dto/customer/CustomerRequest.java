package com.ashutosh.inventory.dto.customer;

import com.ashutosh.inventory.constants.ValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
    @NotBlank(message = ValidationMessages.CUSTOMER_NAME_REQUIRED)
    @Size(min = 3, max = 150,
            message = ValidationMessages.CUSTOMER_NAME_SIZE)
    private String customerName;

    @NotBlank(message = ValidationMessages.CUSTOMER_PHONE_REQUIRED)
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = ValidationMessages.CUSTOMER_INVALID_PHONE
    )
    private String phone;

    @NotBlank(message = ValidationMessages.CUSTOMER_EMAIL_REQUIRED)
    @Email(message = ValidationMessages.CUSTOMER_INVALID_EMAIL)
    private String email;

    @NotBlank(message = ValidationMessages.CUSTOMER_ADDRESS_REQUIRED)
    @Size(max = 300,
            message = ValidationMessages.CUSTOMER_ADDRESS_SIZE)
    private String address;

    @NotBlank(message = ValidationMessages.CUSTOMER_GST_REQUIRED)
    @Size(min = 15, max = 15,
            message = ValidationMessages.CUSTOMER_GST_SIZE)
    @Pattern(
            regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$",
            message = ValidationMessages.CUSTOMER_INVALID_GST
    )
    private String gstNumber;
}
