package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends MongoRepository<Cart, String> {
    Cart findByUserId(String userId);

}
