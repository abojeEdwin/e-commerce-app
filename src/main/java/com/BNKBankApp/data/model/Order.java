package com.BNKBankApp.data.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Data
@Document(collection="Order")
public class Order {
    @Id
    private String id;
    private String userId;
    private String cartId;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private double totalPrice;
    private PaymentMethod paymentMethod;
    private String billingAddressId;
    private String shippingAddressId;
    private OrderHistory orderHistory;
    private String reviewId;
}
