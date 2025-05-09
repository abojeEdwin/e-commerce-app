package com.BNKBankApp.dto.request;
import lombok.Data;

import java.util.Date;

@Data
public class CreateInventoryRequest {
    private String productId;
    private int stockQuantity;
    private Date lastRestockDate;

}
