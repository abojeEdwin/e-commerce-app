package com.BNKBankApp.service;
import com.BNKBankApp.data.model.*;
import com.BNKBankApp.data.repository.OrderHistoryRepo;
import com.BNKBankApp.data.repository.OrderRepo;
import com.BNKBankApp.data.repository.ProductRepo;
import com.BNKBankApp.data.repository.ReviewRepo;
import com.BNKBankApp.dto.request.ProductReviewRequest;
import com.BNKBankApp.dto.resonse.ProductReviewResponse;
import com.BNKBankApp.exception.ExceededNumberOfReviewPerOrderException;
import com.BNKBankApp.exception.NoProductFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CartServiceImpl cartServiceImpl;
    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    OrderHistoryRepo orderHistoryRepo;
    @Autowired
    private OrderServiceImpl orderServiceImpl;


    @Override
    public ProductReviewResponse addReview(ProductReviewRequest productReviewRequest, String orderId, Cart cartResponse) {
        Product product = productServiceImpl.findProduct(productReviewRequest.getProductName());
        Order order = orderServiceImpl.findOrderById(orderId);
        OrderItem neededProduct = null;

        for(OrderItem orderItem : cartResponse.getOrderItem()){
            if(Objects.equals(orderItem.getProductId(), product.getId())){
                neededProduct = orderItem;
            }
        }

        if(neededProduct == null){throw new NoProductFoundException("You have not purchased a product yet");}

        if(order.getReviewId() == null){
            Review review = new Review();
            review.setProductId(product.getId());
            review.setComment(productReviewRequest.getMessage());
            review.setUserId(productReviewRequest.getUserId());
            review.setDate(LocalDateTime.now());
            review.setRating(productReviewRequest.getRating());
            review.setOrderId(order.getId());
            Review savedReview = reviewRepo.save(review);

            double total = review.getRating().getValue();
            double averageRating = product.getAverageRating();
            averageRating = (averageRating + total)/2;
            product.setAverageRating(averageRating);
            productRepo.save(product);

            order.setReviewId(savedReview.getId());
            orderRepo.save(order);

            ProductReviewResponse productReviewResponse = new ProductReviewResponse();
            productReviewResponse.setMessage("Review successfully added");
            return productReviewResponse;
        }
        throw new ExceededNumberOfReviewPerOrderException("You have exceeded the number of reviews allowed");
    }


    @Override
    public void deleteReview() {
        reviewRepo.deleteAll();
    }
}
