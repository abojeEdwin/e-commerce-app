package com.BNKBankApp.service;
import com.BNKBankApp.data.model.*;
import com.BNKBankApp.data.repository.AddressRepo;
import com.BNKBankApp.data.repository.UserRepo;
import com.BNKBankApp.dto.request.AddToCartRequest;
import com.BNKBankApp.dto.request.LoginRequest;
import com.BNKBankApp.dto.request.ProductReviewRequest;
import com.BNKBankApp.dto.request.UserRegisterRequest;
import com.BNKBankApp.dto.resonse.LoginResponse;
import com.BNKBankApp.dto.resonse.ProductReviewResponse;
import com.BNKBankApp.dto.resonse.UserRegisterResponse;
import com.BNKBankApp.util.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    HashPassword hashPassword;
    @Autowired
    AddressServiceImpl addressServiceImpl;
    @Autowired
    private AddressRepo addressRepo;

    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        User user = new User();
//        Address address = userRegisterRequest.getAddress();
        String hashedPassword = HashPassword.hashPassword(userRegisterRequest.getPassword());

        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(hashedPassword);
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setUsername(userRegisterRequest.getUsername());
        user.setRole(Role.User);
        User savedUser = userRepo.save(user);

//        address.setUserId(savedUser.getId());
//        Address savedAddress = addressRepo.save(address);
//        savedUser.setAddress(savedAddress);
        userRepo.save(savedUser);


        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setUsername(savedUser.getUsername());
        userRegisterResponse.setUserId(savedUser.getId());
        userRegisterResponse.setStatus("Success");
        return userRegisterResponse;
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
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
        return userRepo.findById(userId).orElse(null);
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

    @Override
    public void deleteAll() {
        userRepo.deleteAll();
    }

    @Override
    public long count() {
        return userRepo.count();
    }
}
