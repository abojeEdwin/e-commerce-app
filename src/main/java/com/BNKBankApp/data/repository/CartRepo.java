package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Cart;
import com.BNKBankApp.data.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends MongoRepository<Cart, String> {
    Cart findByUserId(String userId);
    Optional<Order> findByOrderId(String orderId);

}
