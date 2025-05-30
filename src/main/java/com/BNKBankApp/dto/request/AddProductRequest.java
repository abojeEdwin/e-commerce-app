package com.BNKBankApp.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddProductRequest {

    @NotBlank(message = "This field cannot be blank")
    private String name;

    @NotBlank(message = "This field cannot be blank")
    private String description;

    @NotBlank(message = "This field cannot be blank")
    private double price;

    @NotBlank(message = "This field cannot be blank")
    private int quantity;

    @NotBlank(message = "This field cannot be blank")
    private String imageUrl;

    @NotBlank(message = "This field cannot be blank")
    private String categoryId;
}
