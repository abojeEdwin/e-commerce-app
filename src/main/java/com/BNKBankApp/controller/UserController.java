package com.BNKBankApp.controller;
import com.BNKBankApp.data.model.Cart;
import com.BNKBankApp.data.model.Category;
import com.BNKBankApp.data.model.Product;
import com.BNKBankApp.data.model.User;
import com.BNKBankApp.dto.request.*;
import com.BNKBankApp.dto.resonse.AddedProductResponse;
import com.BNKBankApp.dto.resonse.LoginResponse;
import com.BNKBankApp.dto.resonse.ProductReviewResponse;
import com.BNKBankApp.dto.resonse.UserRegisterResponse;
import com.BNKBankApp.service.UserServiceImpl;
import com.BNKBankApp.util.Otp;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final Otp otpService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        return ResponseEntity.ok(userServiceImpl.registerUser(userRegisterRequest));
    }

    @PostMapping("/requestOtp")
    public ResponseEntity <?>requestOtp(@RequestBody @Valid EmailRequest emailRequest) {
        otpService.sendOTPEmail(emailRequest.getEmail(),"login");
        return ResponseEntity.ok("OTP Sent");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = userServiceImpl.loginUser(loginRequest);
        if(loginResponse == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/findProductByName")
    public ResponseEntity<Product> findProductByName(@RequestBody @Valid FindProductRequest findProductRequest) {
        return ResponseEntity.ok(userServiceImpl.findProduct(findProductRequest));
    }

    @PostMapping("/findProductByCategory")
    public ResponseEntity <List<Product>> findCategoryByName(@RequestBody @Valid FindCategoryRequest findCategoryRequest){
        return ResponseEntity.ok(userServiceImpl.findProductsByCategoryName(findCategoryRequest));
    }

    @PostMapping("/addToCart")
    public ResponseEntity <Cart> addToCart(@RequestBody List<AddToCartRequest> addToCartRequest) {
        return ResponseEntity.ok(userServiceImpl.addProductToCart(addToCartRequest));
    }

    @PostMapping("/deleteProductFromCartByProductName")
    public ResponseEntity<List<Cart>> removeProductFromCartByProductName(@RequestBody @Valid FindProductRequest findProductRequest){
        return ResponseEntity.ok(userServiceImpl.removeProductFromCartByProductName(findProductRequest));
    }

    @GetMapping("/findUserByName")
    public ResponseEntity<Optional<User>> findUserByUserName (@RequestBody @Valid  FindUserRequest findUserRequest){
        return ResponseEntity.ok(userServiceImpl.findUserByUsername(findUserRequest));
    }

    @DeleteMapping("/removeProductFromCart")
    public ResponseEntity<List<Cart>> removeProductFromCart(@RequestBody @Valid FindProductRequest findProductRequest){
        return ResponseEntity.ok(userServiceImpl.removeProductFromCartByProductName(findProductRequest));
    }

    @PostMapping("/addProductReview")
    public ResponseEntity<ProductReviewResponse> addProductReview(@RequestBody @Valid ProductReviewRequest productReviewRequest, String userId, Cart cartResponse){
        return ResponseEntity.ok(userServiceImpl.productReview(productReviewRequest,userId,cartResponse));
    }
}
