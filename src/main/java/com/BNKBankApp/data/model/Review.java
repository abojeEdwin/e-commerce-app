package com.BNKBankApp.data.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="Review")
@Data
public class Review {
    @Id
    private String id;
    private String productId;
    private String comment;
    private Rating rating;
    private LocalDateTime date;
    private String orderId;
    private String userId;

}
