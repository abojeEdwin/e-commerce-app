package com.BNKBankApp.data.repository;
import com.BNKBankApp.data.model.WishlistItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WishListItemRepo extends MongoRepository<WishlistItem,String> {

}
