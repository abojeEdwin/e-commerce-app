package com.BNKBankApp.data.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection="WishList")
public class WishList {
    @Id
    private String id;
    private String userId;
    private List<WishlistItem> items;
    private LocalDateTime createdAt;
}
