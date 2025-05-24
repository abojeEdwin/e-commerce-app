package com.BNKBankApp.service;
import com.BNKBankApp.data.model.*;
import com.BNKBankApp.dto.request.*;
import com.BNKBankApp.dto.resonse.LoginResponse;
import com.BNKBankApp.dto.resonse.ProductReviewResponse;
import com.BNKBankApp.dto.resonse.UserRegisterResponse;
import com.BNKBankApp.exception.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    AddressServiceImpl addressServiceImpl;
    @Autowired
    ProductServiceImpl productServiceImpl;
    @Autowired
    private CheckOutServiceImpl checkOutServiceImpl;

    @BeforeEach
    void setUp() {
        userServiceImpl.deleteAll();
        addressServiceImpl.deleteAll();
//        productServiceImpl.deleteAllProducts();
    }
    @AfterEach
    void tearDown() {
        userServiceImpl.deleteAll();
        addressServiceImpl.deleteAll();
//        productServiceImpl.deleteAllProducts();
    }

    @Test
    public void testUserCanRegister() {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("Osundu");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setEmail("abojeedwin@gmail.com");
        userRegisterRequest.setFirstName("Benjamin");
        userRegisterRequest.setLastName("Jacob");
        userRegisterRequest.setPhoneNumber("1234567890");

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity("Lagos");
        addressRequest.setPostalCode("9410");
        addressRequest.setCountry("Nigeria");
        addressRequest.setStreetNumber("17");
        addressRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest,addressRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());
    }

    @Test
    public void testUserRegisterWthDuplicateEmail(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("Osundu");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setEmail("abojeedwin@gmail.com");
        userRegisterRequest.setFirstName("Benjamin");
        userRegisterRequest.setLastName("Jacob");
        userRegisterRequest.setPhoneNumber("1234567890");

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity("Lagos");
        addressRequest.setPostalCode("9410");
        addressRequest.setCountry("Nigeria");
        addressRequest.setStreetNumber("17");
        addressRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest,addressRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());

        UserRegisterRequest userRegisterRequest1 = new UserRegisterRequest();
        userRegisterRequest1.setUsername("Osundu");
        userRegisterRequest1.setPassword("password");
        userRegisterRequest1.setEmail("abojeedwin@gmail");
        userRegisterRequest1.setFirstName("Benjamin");
        userRegisterRequest1.setLastName("Jacob");
        userRegisterRequest1.setPhoneNumber("1234567890");

        AddressRequest addressRequest1 = new AddressRequest();
        addressRequest1.setCity("Lagos");
        addressRequest1.setPostalCode("9410");
        addressRequest1.setCountry("Nigeria");
        addressRequest1.setStreetNumber("17");
        addressRequest1.setLgaName("Bariga");

        assertThrows(EmailAlreadyExistException.class,()->userServiceImpl.registerUser(userRegisterRequest,addressRequest));
    }

    @Test
    public void testUserRegisterWithInvalidEmail(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("Osundu");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setEmail("abojeedwingmail");
        userRegisterRequest.setFirstName("Benjamin");
        userRegisterRequest.setLastName("Jacob");
        userRegisterRequest.setPhoneNumber("1234567890");

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity("Lagos");
        addressRequest.setPostalCode("9410");
        addressRequest.setCountry("Nigeria");
        addressRequest.setStreetNumber("17");
        addressRequest.setLgaName("Bariga");

        assertThrows(InvalidEmailException.class,()->userServiceImpl.registerUser(userRegisterRequest,addressRequest));
    }

    @Test
    public void testUserRegisterWithDuplicateUsername(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("Osundu");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setEmail("abojeedwin@gmail.com");
        userRegisterRequest.setFirstName("Benjamin");
        userRegisterRequest.setLastName("Jacob");
        userRegisterRequest.setPhoneNumber("1234567890");

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity("Lagos");
        addressRequest.setPostalCode("9410");
        addressRequest.setCountry("Nigeria");
        addressRequest.setStreetNumber("17");
        addressRequest.setLgaName("Bariga");
        userServiceImpl.registerUser(userRegisterRequest,addressRequest);


        UserRegisterRequest userRegisterRequest1 = new UserRegisterRequest();
        userRegisterRequest1.setUsername("Osundu");
        userRegisterRequest1.setPassword("password");
        userRegisterRequest1.setEmail("jiggydem@gmail.com");
        userRegisterRequest1.setFirstName("Benjamin");
        userRegisterRequest1.setLastName("Jacob");
        userRegisterRequest1.setPhoneNumber("1234567890");

        AddressRequest addressRequest1 = new AddressRequest();
        addressRequest1.setCity("Lagos");
        addressRequest1.setPostalCode("9410");
        addressRequest1.setCountry("Nigeria");
        addressRequest1.setStreetNumber("17");
        addressRequest1.setLgaName("Bariga");

        assertThrows(DuplicateUsernameException.class,()->userServiceImpl.registerUser(userRegisterRequest1,addressRequest1));
    }

    @Test
    public void testUserLogin(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("Osundu");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setEmail("abojeedwin@gmail.com");
        userRegisterRequest.setFirstName("Benjamin");
        userRegisterRequest.setLastName("Jacob");
        userRegisterRequest.setPhoneNumber("1234567890");

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity("Lagos");
        addressRequest.setPostalCode("9410");
        addressRequest.setCountry("Nigeria");
        addressRequest.setStreetNumber("17");
        addressRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest,addressRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("abojeedwin@gmail.com");
        loginRequest.setPassword("password");
        LoginResponse loginResponse = userServiceImpl.loginUser(loginRequest);
        assertEquals("Success",loginResponse.getStatus());
    }

    @Test
    public void testUserLoginWithInvalidEmail(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("Osundu");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setEmail("abojeedwin@gmail.com");
        userRegisterRequest.setFirstName("Benjamin");
        userRegisterRequest.setLastName("Jacob");
        userRegisterRequest.setPhoneNumber("1234567890");

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity("Lagos");
        addressRequest.setPostalCode("9410");
        addressRequest.setCountry("Nigeria");
        addressRequest.setStreetNumber("17");
        addressRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest,addressRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jiggydem@gmail.com");
        loginRequest.setPassword("password");
        assertThrows(UserNotFoundException.class,()->userServiceImpl.loginUser(loginRequest));
    }

    @Test
    public void testUserCanFindProductByName(){

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("Osundu");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setEmail("abojeedwin@gmail.com");
        userRegisterRequest.setFirstName("Benjamin");
        userRegisterRequest.setLastName("Jacob");
        userRegisterRequest.setPhoneNumber("1234567890");

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity("Lagos");
        addressRequest.setPostalCode("9410");
        addressRequest.setCountry("Nigeria");
        addressRequest.setStreetNumber("17");
        addressRequest.setLgaName("Bariga");


        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest,addressRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());

        Product foundProduct = userServiceImpl.findProduct("Yam");
        assertEquals("Coco Yam", foundProduct.getDescription());
        assertEquals(1000.0,foundProduct.getPrice());
    }

    @Test
    public void testUserFindProductThatDoesNotExistException(){

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("Osundu");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setEmail("abojeedwin@gmail.com");
        userRegisterRequest.setFirstName("Benjamin");
        userRegisterRequest.setLastName("Jacob");
        userRegisterRequest.setPhoneNumber("1234567890");

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity("Lagos");
        addressRequest.setPostalCode("9410");
        addressRequest.setCountry("Nigeria");
        addressRequest.setStreetNumber("17");
        addressRequest.setLgaName("Bariga");


        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest,addressRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());
        assertThrows(NoProductFoundException.class,()->userServiceImpl.findProduct("Garri"));
    }

    @Test
    public void testUserFindProductByCategory(){

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("Osundu");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setEmail("abojeedwin@gmail.com");
        userRegisterRequest.setFirstName("Benjamin");
        userRegisterRequest.setLastName("Jacob");
        userRegisterRequest.setPhoneNumber("1234567890");

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity("Lagos");
        addressRequest.setPostalCode("9410");
        addressRequest.setCountry("Nigeria");
        addressRequest.setStreetNumber("17");
        addressRequest.setLgaName("Bariga");


        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest,addressRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());

       List<Product> foundProduct = userServiceImpl.findProductsByCategoryName("Food Stuff");
        assertEquals("Yam", foundProduct.get(0).getName());
        assertEquals(2,foundProduct.size());
    }
    @Test
    public void testFindUserById(){
        User user = new User();
        user.setUsername("jiggy211");
        user.setPassword("password");
        user.setFirstName("Benjamin");
        user.setLastName("Jacob");
        user.setPhoneNumber("1234567890");
        user.setEmail("aboje@gmail.com");

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername(user.getUsername());
        userRegisterRequest.setPassword(user.getPassword());
        userRegisterRequest.setEmail(user.getEmail());
        userRegisterRequest.setFirstName(user.getFirstName());
        userRegisterRequest.setLastName(user.getLastName());
        userRegisterRequest.setPhoneNumber(user.getPhoneNumber());
        userRegisterRequest.setId(user.getId());

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity("Lagos");
        addressRequest.setPostalCode("9410");
        addressRequest.setCountry("Nigeria");
        addressRequest.setStreetNumber("17");
        addressRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest,addressRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("jiggy211",registerResponse.getUsername());
        assertEquals("jiggy211", userServiceImpl.findUserById(registerResponse.getUserId()).getUsername());
    }

    @Test
    public void testUserCanAddProductToCart(){
        User user = new User();
        user.setUsername("Osundu");
        user.setPassword("password");
        user.setFirstName("Benjamin");
        user.setLastName("Jacob");
        user.setPhoneNumber("1234567890");
        user.setEmail("abojeedwin@gmail.com");

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername(user.getUsername());
        userRegisterRequest.setPassword(user.getPassword());
        userRegisterRequest.setEmail(user.getEmail());
        userRegisterRequest.setFirstName(user.getFirstName());
        userRegisterRequest.setLastName(user.getLastName());
        userRegisterRequest.setPhoneNumber(user.getPhoneNumber());
        userRegisterRequest.setId(user.getId());

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity("Lagos");
        addressRequest.setPostalCode("9410");
        addressRequest.setCountry("Nigeria");
        addressRequest.setStreetNumber("17");
        addressRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest,addressRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());

        AddToCartRequest cartRequest1 = new AddToCartRequest();
        cartRequest1.setProductName("Yam");
        cartRequest1.setQuantity(1);
        cartRequest1.setUserId(registerResponse.getUserId());

        AddToCartRequest cartRequest2 = new AddToCartRequest();
        cartRequest2.setProductName("Yam");
        cartRequest2.setQuantity(100);
        cartRequest2.setUserId(registerResponse.getUserId());

        AddToCartRequest cartRequest3 = new AddToCartRequest();
        cartRequest3.setProductName("Grand pure soya oil");
        cartRequest3.setQuantity(1);
        cartRequest3.setUserId(registerResponse.getUserId());

        List<AddToCartRequest> cartRequests = List.of(cartRequest1,cartRequest2,cartRequest3);
        Cart cartResponse = userServiceImpl.addProductToCart(cartRequests, registerResponse.getUserId());
        assertEquals(3,cartResponse.getOrderItem().size());
    }

    @Test
    public void testUserCanCheck(){
        User user = new User();
        user.setUsername("Osundu");
        user.setPassword("password");
        user.setFirstName("Benjamin");
        user.setLastName("Jacob");
        user.setPhoneNumber("1234567890");
        user.setEmail("abojeedwin@gmail.com");

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername(user.getUsername());
        userRegisterRequest.setPassword(user.getPassword());
        userRegisterRequest.setEmail(user.getEmail());
        userRegisterRequest.setFirstName(user.getFirstName());
        userRegisterRequest.setLastName(user.getLastName());
        userRegisterRequest.setPhoneNumber(user.getPhoneNumber());
        userRegisterRequest.setId(user.getId());

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity("Lagos");
        addressRequest.setPostalCode("9410");
        addressRequest.setCountry("Nigeria");
        addressRequest.setStreetNumber("17");
        addressRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest,addressRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());

        AddToCartRequest cartRequest1 = new AddToCartRequest();
        cartRequest1.setProductName("Yam");
        cartRequest1.setQuantity(1);
        cartRequest1.setUserId(registerResponse.getUserId());

        AddToCartRequest cartRequest2 = new AddToCartRequest();
        cartRequest2.setProductName("Yam");
        cartRequest2.setQuantity(100);
        cartRequest2.setUserId(registerResponse.getUserId());

        AddToCartRequest cartRequest3 = new AddToCartRequest();
        cartRequest3.setProductName("Grand pure soya oil");
        cartRequest3.setQuantity(1);
        cartRequest3.setUserId(registerResponse.getUserId());

        List<AddToCartRequest> cartRequests = List.of(cartRequest1,cartRequest2,cartRequest3);
        Cart cartResponse = userServiceImpl.addProductToCart(cartRequests, registerResponse.getUserId());
        assertEquals(3,cartResponse.getOrderItem().size());
        Order checkOutOrder = checkOutServiceImpl.checkOut(registerResponse.getUserId());
        assertEquals(checkOutOrder.getOrderStatus(), OrderStatus.COMPLETED_USER);
    }
    @Test
    public void testUserCanRemoveFromCart(){
        User user = new User();
        user.setUsername("Osundu");
        user.setPassword("password");
        user.setFirstName("Benjamin");
        user.setLastName("Jacob");
        user.setPhoneNumber("1234567890");
        user.setEmail("abojeedwin@gmail.com");

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername(user.getUsername());
        userRegisterRequest.setPassword(user.getPassword());
        userRegisterRequest.setEmail(user.getEmail());
        userRegisterRequest.setFirstName(user.getFirstName());
        userRegisterRequest.setLastName(user.getLastName());
        userRegisterRequest.setPhoneNumber(user.getPhoneNumber());
        userRegisterRequest.setId(user.getId());

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity("Lagos");
        addressRequest.setPostalCode("9410");
        addressRequest.setCountry("Nigeria");
        addressRequest.setStreetNumber("17");
        addressRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest,addressRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());

        AddToCartRequest cartRequest1 = new AddToCartRequest();
        cartRequest1.setProductName("Yam");
        cartRequest1.setQuantity(1);
        cartRequest1.setUserId(registerResponse.getUserId());

        AddToCartRequest cartRequest2 = new AddToCartRequest();
        cartRequest2.setProductName("Yam");
        cartRequest2.setQuantity(100);
        cartRequest2.setUserId(registerResponse.getUserId());

        AddToCartRequest cartRequest3 = new AddToCartRequest();
        cartRequest3.setProductName("Grand pure soya oil");
        cartRequest3.setQuantity(1);
        cartRequest3.setUserId(registerResponse.getUserId());

        List<AddToCartRequest> cartRequests = List.of(cartRequest1,cartRequest2,cartRequest3);
        Cart cartResponse = userServiceImpl.addProductToCart(cartRequests, registerResponse.getUserId());
        assertEquals(3,cartResponse.getOrderItem().size());
        Order checkOutOrder = checkOutServiceImpl.checkOut(registerResponse.getUserId());
        assertEquals(checkOutOrder.getOrderStatus(), OrderStatus.COMPLETED_USER);

        List<Cart> newCartList = userServiceImpl.removeProductFromCartByProductName("Grand pure soya oil");
        Cart firstCart = newCartList.get(0);
        assertEquals(2,firstCart.getOrderItem().size());

    }

    @Test
    public void testUserCanAddProductReview(){
        User user = new User();
        user.setUsername("Osundu");
        user.setPassword("password");
        user.setFirstName("Benjamin");
        user.setLastName("Jacob");
        user.setPhoneNumber("1234567890");
        user.setEmail("abojeedwin@gmail.com");

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername(user.getUsername());
        userRegisterRequest.setPassword(user.getPassword());
        userRegisterRequest.setEmail(user.getEmail());
        userRegisterRequest.setFirstName(user.getFirstName());
        userRegisterRequest.setLastName(user.getLastName());
        userRegisterRequest.setPhoneNumber(user.getPhoneNumber());
        userRegisterRequest.setId(user.getId());

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity("Lagos");
        addressRequest.setPostalCode("9410");
        addressRequest.setCountry("Nigeria");
        addressRequest.setStreetNumber("17");
        addressRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest,addressRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());

        AddToCartRequest cartRequest1 = new AddToCartRequest();
        cartRequest1.setProductName("Yam");
        cartRequest1.setQuantity(1);
        cartRequest1.setUserId(registerResponse.getUserId());

        AddToCartRequest cartRequest2 = new AddToCartRequest();
        cartRequest2.setProductName("Yam");
        cartRequest2.setQuantity(100);
        cartRequest2.setUserId(registerResponse.getUserId());

        AddToCartRequest cartRequest3 = new AddToCartRequest();
        cartRequest3.setProductName("Grand pure soya oil");
        cartRequest3.setQuantity(1);
        cartRequest3.setUserId(registerResponse.getUserId());

        List<AddToCartRequest> cartRequests = List.of(cartRequest1,cartRequest2,cartRequest3);
        Cart cartResponse = userServiceImpl.addProductToCart(cartRequests, registerResponse.getUserId());
        assertEquals(3,cartResponse.getOrderItem().size());
        Order checkOutOrder = checkOutServiceImpl.checkOut(registerResponse.getUserId());
        assertEquals(checkOutOrder.getOrderStatus(), OrderStatus.COMPLETED_USER);

        List<Cart> newCartList = userServiceImpl.removeProductFromCartByProductName("Grand pure soya oil");
        Cart firstCart = newCartList.get(0);
        assertEquals(2,firstCart.getOrderItem().size());
        ProductReviewRequest productReviewRequest = new ProductReviewRequest();
        productReviewRequest.setProductName("Yam");
        productReviewRequest.setUserId(registerResponse.getUserId());
        productReviewRequest.setMessage("Nice Product");
        productReviewRequest.setRating(Rating.FOUR);

        ProductReviewResponse productResponse = userServiceImpl.productReview(productReviewRequest,checkOutOrder.getId(),firstCart);
        assertEquals("Review successfully added",productResponse.getMessage());

    }
}