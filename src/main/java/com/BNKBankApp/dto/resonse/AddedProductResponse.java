package com.BNKBankApp.dto.resonse;
import lombok.Data;



@Data
public class AddedProductResponse {

    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String imageUrl;
    private String category;
    private String status;
}
