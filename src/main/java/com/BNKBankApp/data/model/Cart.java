package com.BNKBankApp.data.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;


@Document(collection="Cart")
@Data
public class Cart {

    @Id
    private String id;
    private String userId;
    private LocalDateTime createdAt;
    private String orderId;
    private List<OrderItem> orderItem;
    private double totalPrice;

}
