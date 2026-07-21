package com.ashutosh.inventory.dto.supplier;

import com.ashutosh.inventory.constants.ValidationMessages;
import com.ashutosh.inventory.enums.PaymentMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRequest {

    @NotBlank(message = ValidationMessages.SUPPLIER_NAME_REQUIRED)
    @Size(min = 3, max = 150,
            message = ValidationMessages.SUPPLIER_NAME_SIZE)
    private String supplierName;

    @Size(max = 100,
            message = ValidationMessages.CONTACT_PERSON_SIZE)
    private String contactPerson;

    @NotBlank(message = ValidationMessages.PHONE_REQUIRED)
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = ValidationMessages.INVALID_PHONE
    )
    private String phone;

    @Email(message = ValidationMessages.INVALID_EMAIL)
    private String email;

    @Pattern(
            regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[A-Z0-9]{3}$",
            message = ValidationMessages.INVALID_GST
    )
    @Size(min = 15, max = 15,
            message = ValidationMessages.GST_NUMBER_SIZE)
    private String gstNumber;

    @Size(max = 300,
            message = ValidationMessages.ADDRESS_SIZE)
    private String address;

    @NotNull(message = ValidationMessages.PAYMENT_MODE_REQUIRED)
    private PaymentMode paymentMode;

    @Min(value = 0,
            message = ValidationMessages.CREDIT_DAYS_INVALID)
    private Integer creditDays;
}
