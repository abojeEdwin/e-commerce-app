package com.BNKBankApp.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
@NotNull
@NotBlank
public class AddProductRequest {
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String imageUrl;
    private String categoryId;
}
