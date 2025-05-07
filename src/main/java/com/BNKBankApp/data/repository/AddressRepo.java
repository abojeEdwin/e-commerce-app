package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepo extends MongoRepository<Address, String> {
}
