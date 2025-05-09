package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Review;

public interface ReviewService {
    Review saveReview(Review review);
    void deleteReview();
}
