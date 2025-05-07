package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepo extends MongoRepository<Cart, String> {

}
