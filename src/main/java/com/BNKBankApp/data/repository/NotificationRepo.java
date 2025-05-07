package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepo extends MongoRepository<Notification, String> {
}
