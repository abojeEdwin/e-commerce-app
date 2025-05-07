package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AdminRepo extends MongoRepository<Admin, String> {

    Admin findByUsername(String username);
    boolean existsByEmail(String email);
    Admin findByEmail(String email);
    boolean existsByUsername(String username);

}
