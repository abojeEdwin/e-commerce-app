package com.BNKBankApp.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class AddToCartRequest {

    @NotBlank(message="This field cannot be empty")
    @NotNull
    private String productName;
    @NotBlank(message="This field cannot be empty")
    @NotNull
    private int quantity;
    @NotBlank(message="This field cannot be empty")
    @NotNull
    private String userId;
}
