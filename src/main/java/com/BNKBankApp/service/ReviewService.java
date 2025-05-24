package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Cart;
import com.BNKBankApp.data.model.Review;
import com.BNKBankApp.dto.request.ProductReviewRequest;
import com.BNKBankApp.dto.resonse.ProductReviewResponse;

public interface ReviewService {
    ProductReviewResponse addReview(ProductReviewRequest productReviewRequest, String orderId, Cart cartResponse);
    void deleteReview();
}
