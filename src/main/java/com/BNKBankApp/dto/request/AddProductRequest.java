package com.BNKBankApp.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddProductRequest {

    @NotBlank(message = "This field cannot be blank")
    private String name;

    @NotBlank(message = "This field cannot be blank")
    private String description;

    @NotNull(message = "This field cannot be blank")
    private double price;

    @NotNull(message = "This field field is required")
    private int quantity;

    @NotBlank(message = "This field cannot be blank")
    private String imageUrl;

    @NotBlank(message = "This field cannot be blank")
    private String categoryId;
}
