package com.BNKBankApp.dto.request;
import jakarta.validation.constraints.NotBlank;;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class AddToCartRequest {

    @NotBlank(message="This field cannot be empty")
    private String productName;

    @NotBlank(message="This field cannot be empty")
    private int quantity;

    @NotBlank(message="This field cannot be empty")
    private String userId;

}
