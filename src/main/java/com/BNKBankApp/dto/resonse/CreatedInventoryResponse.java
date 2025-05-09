package com.BNKBankApp.dto.resonse;
import lombok.Data;
import java.util.Date;


@Data
public class CreatedInventoryResponse {
    private String status;
    private String productId;
    private int stockQuantity;
    private Date lastRestockedDate;

}
