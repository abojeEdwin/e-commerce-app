package com.BNKBankApp.data.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="WishListItem")
public class WishlistItem {

    @Id
    private String id;
    private Product product;
    private String wishlistId;

}
