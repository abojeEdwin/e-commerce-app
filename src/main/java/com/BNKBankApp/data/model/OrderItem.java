package com.BNKBankApp.data.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="OrderItem")
public class OrderItem {

    @Id
    private String id;
    private String orderId;
    private String productId;
    private String userId;
}
