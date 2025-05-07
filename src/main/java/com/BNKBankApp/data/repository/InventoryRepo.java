package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepo extends MongoRepository<Inventory, String> {
    Inventory findByProductId(String productId);

}
