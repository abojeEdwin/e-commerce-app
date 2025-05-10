package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepo extends MongoRepository<Product, String> {
    Product findProductByName(String name);
    void deleteProductByName(String name);
    List<Product> findProductById(String id);
    List<Product> findByCategoryId(String categoryId);
}
