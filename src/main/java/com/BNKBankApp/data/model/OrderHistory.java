package com.BNKBankApp.data.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection="OrderHistory")
@Data
public class OrderHistory {
    @Id
    private String id;
    private String orderId;
    private String userId;
    private List<OrderItem> orderItems;
}
