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
import com.BNKBankApp.util.HashPassword;
import com.BNKBankApp.util.Jwt;
import com.BNKBankApp.util.VerifyEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private VerifyEmail verifyEmail;
    private static HashPassword hashPassword;
    @Autowired
    private Jwt jwtService;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest, AddressRequest addressRequest) {
        if(userRepo.existsByEmail(userRegisterRequest.getEmail())){throw new EmailAlreadyExistException("Email already exist");}
        if(!verifyEmail.isVerifiedEmail(userRegisterRequest.getEmail())){throw new InvalidEmailException("Invalid email, please try again.");}
        if(userRepo.existsByUsername(userRegisterRequest.getUsername())){throw new DuplicateUsernameException("Username already exist");}

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
        address.setPostalCode(addressRequest.getPostalCode());
        address.setCity(addressRequest.getCity());
        address.setCountry(addressRequest.getCountry());
        address.setUserId(user.getId());
        address.setLgaName(addressRequest.getLgaName());
        address.setStreetName(addressRequest.getStreetName());
        address.setStreetNumber(addressRequest.getStreetNumber());
        addressRepo.save(address);

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
       return new LoginResponse(loginResponse.getToken(),loginResponse.getStatus());
    }

    @Override
    public Product findProduct(String productName) {
        Product foundProduct = productRepo.findProductByName(productName);
        if(foundProduct == null){throw new NoProductFoundException("Product Not Found");}return foundProduct;}

    @Override
    public List<Product> findProductsByCategoryName(String category) {
        Category foundCategory = categoryServiceImpl.findByName(category);
        List<Product> product = productRepo.findByCategoryId(foundCategory.getId());
        return product;
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
