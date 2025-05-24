package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends MongoRepository<Review, String> {

}
