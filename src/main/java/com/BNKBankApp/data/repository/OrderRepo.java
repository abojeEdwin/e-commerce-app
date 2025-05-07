package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<Order, String> {
    Order findOrderById(String id);
}
