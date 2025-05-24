package com.BNKBankApp.dto.request;
import com.BNKBankApp.data.model.Rating;
import lombok.Data;

@Data
public class ProductReviewRequest {
    private String productName;
    private String message;
    private Rating rating;
    private String userId;
}
