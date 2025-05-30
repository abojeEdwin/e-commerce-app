package com.BNKBankApp.service;
import com.BNKBankApp.data.model.*;
import com.BNKBankApp.data.repository.AddressRepo;
import com.BNKBankApp.data.repository.ProductRepo;
import com.BNKBankApp.data.repository.UserRepo;
import com.BNKBankApp.dto.request.*;
import com.BNKBankApp.dto.resonse.LoginResponse;
import com.BNKBankApp.dto.resonse.ProductReviewResponse;
import com.BNKBankApp.dto.resonse.UserRegisterResponse;
import com.BNKBankApp.exception.*;
import com.BNKBankApp.util.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private VerifyEmail verifyEmail;
    @Autowired
    private Jwt jwtService;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @Autowired
    private CheckOutServiceImpl checkOutServiceImpl;
    @Autowired
    Otp otpService;
    @Autowired
    private CartServiceImpl cartServiceImpl;
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private ReviewServiceImpl reviewServiceImpl;
    @Autowired
    VerifyUser verifyUser;


    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        verifyUser.registerUser(userRegisterRequest);
        User user = new User();
        String hashedPassword = HashPassword.hashPassword(userRegisterRequest.getPassword());
        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(hashedPassword);
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setUsername(userRegisterRequest.getUsername());
        user.setRole(Role.User);
        User savedUser = userRepo.save(user);

        Address address = new Address();
        address.setPostalCode(userRegisterRequest.getPostalCode());
        address.setCity(userRegisterRequest.getCity());
        address.setCountry(userRegisterRequest.getCountry());
        address.setUserId(user.getId());
        address.setLgaName(userRegisterRequest.getLgaName());
        address.setStreetName(userRegisterRequest.getStreetName());
        address.setStreetNumber(userRegisterRequest.getStreetNumber());
        Address savedAddress = addressRepo.save(address);
        user.setAddressId(savedAddress.getId());
        userRepo.save(savedUser);

        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setUsername(savedUser.getUsername());
        userRegisterResponse.setUserId(savedUser.getId());
        userRegisterResponse.setStatus("Success");
        return userRegisterResponse;
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
       User foundUser = userRepo.findByEmail(loginRequest.getEmail());
       if(foundUser == null){throw new UserNotFoundException("User not found");}
       if(!HashPassword.verifyPassword(foundUser.getPassword(), loginRequest.getPassword())){throw new InvalidPasswordException("Incorrect Password, Try Again");}
       String token = jwtService.generateToken(foundUser.getUsername());
       LoginResponse loginResponse = new LoginResponse();
       loginResponse.setStatus("Success");
       loginResponse.setToken(token);
       otpService.verifyOTPAndGenerateToken(loginRequest.getEmail(), loginRequest.getOtp());
       return new LoginResponse(loginResponse.getToken(),loginResponse.getStatus());
    }

    @Override
    public Product findProduct(FindProductRequest findProductRequest) {
        Product foundProduct = productRepo.findProductByName(findProductRequest.getProductName());
        if(foundProduct == null){throw new NoProductFoundException("Product Not Found");}return foundProduct;}

    @Override
    public List<Product> findProductsByCategoryName(FindCategoryRequest findCategoryRequest) {
        Category foundCategory = categoryServiceImpl.findByName(findCategoryRequest.getCategoryName());
        if(foundCategory.getName() == null){throw new NoProductFoundException("No Product Found");}
        List<Product> product = productRepo.findByCategoryId(foundCategory.getId());
        return product;
    }

    @Override
    public Cart addProductToCart(List<AddToCartRequest> addToCartRequests) {return checkOutServiceImpl.addToCart(addToCartRequests);}

    @Override
    public User findUserById(String userId) {return userRepo.findById(userId).orElse(null);}

    @Override
    public List<Cart> removeProductFromCartByProductName(FindProductRequest findProductRequest) {return cartServiceImpl.removeProductFromCart(findProductRequest.getProductName());}

    @Override
    public ProductReviewResponse productReview(ProductReviewRequest productReviewRequest, String orderId, String cartResponseId) {return reviewServiceImpl.addReview(productReviewRequest,orderId, cartResponseId);}

    @Override
    public Optional<User> findUserByUsername(FindUserRequest findUserRequest) {
        return userRepo.findByUsername(findUserRequest.getUsername());
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
