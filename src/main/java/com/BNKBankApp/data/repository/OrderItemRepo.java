package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderItemRepo extends MongoRepository<OrderItem, String> {
    void removeOrderItemByProductId(String productId);
    OrderItem findOrderItemById(String id);
}
