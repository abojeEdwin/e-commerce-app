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

public class UserServiceImpl implements UserService {
    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        return null;
    }

    @Override
    public Product findProduct(String productName) {
        return null;
    }

    @Override
    public List<Product> findProductsByCategoryName(String category) {
        return List.of();
    }

    @Override
    public Cart addProductToCart(List<AddToCartRequest> addToCartRequests) {
        return null;
    }

    @Override
    public User findUserById(String userId) {
        return null;
    }

    @Override
    public List<Cart> removeProductFromCartByProductName(String productName) {
        return List.of();
    }

    @Override
    public ProductReviewResponse productReview(ProductReviewRequest productReviewRequest) {
        return null;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.empty();
    }
}
