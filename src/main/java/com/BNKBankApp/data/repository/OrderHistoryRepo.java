package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.OrderHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderHistoryRepo extends MongoRepository<OrderHistory, String> {


}
