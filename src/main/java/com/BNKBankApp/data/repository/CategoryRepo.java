package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends MongoRepository<Category, String> {
    Category findCategoryByName(String name);

}
