package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Cart;
import com.BNKBankApp.data.model.Product;
import com.BNKBankApp.data.model.User;
import com.BNKBankApp.dto.request.AddToCartRequest;
import com.BNKBankApp.dto.request.ProductReviewRequest;
import com.BNKBankApp.dto.request.UserRegisterRequest;
import com.BNKBankApp.dto.resonse.ProductReviewResponse;
import com.BNKBankApp.dto.resonse.UserRegisterResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest);
    Product findProduct(String productName);
    List<Product> findProductsByCategoryName(String category);
    Cart addProductToCart(List<AddToCartRequest> addToCartRequests);
    User findUserById(String userId);
    List <Cart> removeProductFromCartByProductName(String productName);
    ProductReviewResponse productReview(ProductReviewRequest productReviewRequest);
    Optional<User> findUserByUsername(String username);

}
