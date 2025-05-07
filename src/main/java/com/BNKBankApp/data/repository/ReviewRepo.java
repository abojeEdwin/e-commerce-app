package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepo extends MongoRepository<Review, String> {

}
