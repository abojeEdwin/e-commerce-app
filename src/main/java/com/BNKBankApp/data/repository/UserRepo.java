package com.BNKBankApp.data.repository;

import com.BNKBankApp.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    User findUserById(String id);
    User findByUsername(String username);
    boolean existsByEmail(String email);
    User findByEmail(String email);
    boolean existsByUsername(String username);

}
