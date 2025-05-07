package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product, String> {
    Product findProductByName(String name);
    void deleteProductByName(String name);

}
