package com.BNKBankApp.service;
import com.BNKBankApp.data.model.Cart;
import com.BNKBankApp.data.model.Product;
import com.BNKBankApp.data.model.User;
import com.BNKBankApp.dto.request.*;
import com.BNKBankApp.dto.resonse.LoginResponse;
import com.BNKBankApp.dto.resonse.ProductReviewResponse;
import com.BNKBankApp.dto.resonse.UserRegisterResponse;
import java.util.List;
import java.util.Optional;

public interface UserService {
    UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest);
    LoginResponse loginUser(LoginRequest loginRequest);
    Product findProduct(FindProductRequest findProductRequest);
    List<Product> findProductsByCategoryName(FindCategoryRequest findCategoryRequest);
    Cart addProductToCart(List<AddToCartRequest> addToCartRequests);
    User findUserById(String userId);
    List <Cart> removeProductFromCartByProductName(String productName);
    ProductReviewResponse productReview(ProductReviewRequest productReviewRequest,String orderId, Cart cartResponse);
    Optional<User> findUserByUsername(String username);
    void deleteAll();
    long count();

}
