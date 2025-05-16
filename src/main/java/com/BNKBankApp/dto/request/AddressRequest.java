package com.BNKBankApp.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressRequest {
    @NotBlank
    @NotNull
    private String userId;
    @NotBlank
    private String streetNumber;
    @NotBlank
    private String streetName;
    @NotBlank
    private String city;
    @NotBlank
    @NotEmpty(message="This field cannot be empty")
    private String postalCode;
    @NotBlank
    @NotNull
    @NotEmpty(message="This field cannot be empty")
    private String country;
    @NotBlank
    @NotEmpty(message="This feild cannot be empty")
    @NotNull(message="This field cannot be null")
    private String lgaName;

}
