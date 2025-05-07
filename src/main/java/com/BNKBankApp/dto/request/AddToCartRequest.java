package com.BNKBankApp.dto.request;
import lombok.Data;



@Data
public class AddToCartRequest {

    private String productName;
    private int quantity;
    private String userId;
}
