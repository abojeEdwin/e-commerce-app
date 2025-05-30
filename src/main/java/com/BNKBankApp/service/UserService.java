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
    List <Cart> removeProductFromCartByProductName(FindProductRequest findProductRequest);
    ProductReviewResponse productReview(ProductReviewRequest productReviewRequest,String orderId, String cartResponseId);
    Optional<User> findUserByUsername(FindUserRequest findUserRequest);
    void deleteAll();
    long count();
}
