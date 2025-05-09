package com.BNKBankApp.dto.request;
import lombok.Data;



@Data
public class AddProductRequest {
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String imageUrl;
    private String categoryId;
}
