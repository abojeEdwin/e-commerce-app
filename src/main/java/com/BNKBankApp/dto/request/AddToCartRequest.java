package com.BNKBankApp.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class AddToCartRequest {

    @NotBlank(message="This field cannot be empty")
    private String productName;

    @NotNull(message="This field is required")
    private int quantity;

    @NotBlank(message="This field cannot be empty")
    private String userId;

}
