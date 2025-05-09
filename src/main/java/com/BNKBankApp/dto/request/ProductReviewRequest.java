package com.BNKBankApp.dto.request;
import com.BNKBankApp.data.model.Rating;
import lombok.Data;

@Data
public class ProductReviewRequest {
    String productName;
    String message;
    private Rating rating;

}
