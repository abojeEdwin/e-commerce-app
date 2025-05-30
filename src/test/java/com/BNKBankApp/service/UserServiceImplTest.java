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
    @Autowired
    private CartServiceImpl cartServiceImpl;

    @BeforeEach
    void setUp() {
        userServiceImpl.deleteAll();
        addressServiceImpl.deleteAll();
//        cartServiceImpl.deleteAllCart();
//        productServiceImpl.deleteAllProducts();
    }
    @AfterEach
    void tearDown() {
        userServiceImpl.deleteAll();
        addressServiceImpl.deleteAll();
//        cartServiceImpl.deleteAllCart();
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
        userRegisterRequest.setCity("Lagos");
        userRegisterRequest.setPostalCode("9410");
        userRegisterRequest.setCountry("Nigeria");
        userRegisterRequest.setStreetNumber("17");
        userRegisterRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest);
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
        userRegisterRequest.setCity("Lagos");
        userRegisterRequest.setPostalCode("9410");
        userRegisterRequest.setCountry("Nigeria");
        userRegisterRequest.setStreetNumber("17");
        userRegisterRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());

        UserRegisterRequest userRegisterRequest1 = new UserRegisterRequest();
        userRegisterRequest1.setUsername("Osundu");
        userRegisterRequest1.setPassword("password");
        userRegisterRequest1.setEmail("abojeedwin@gmail");
        userRegisterRequest1.setFirstName("Benjamin");
        userRegisterRequest1.setLastName("Jacob");
        userRegisterRequest1.setPhoneNumber("1234567890");
        userRegisterRequest1.setCity("Lagos");
        userRegisterRequest1.setPostalCode("9410");
        userRegisterRequest1.setCountry("Nigeria");
        userRegisterRequest1.setStreetNumber("17");
        userRegisterRequest1.setLgaName("Bariga");

        assertThrows(EmailAlreadyExistException.class,()->userServiceImpl.registerUser(userRegisterRequest));
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
        userRegisterRequest.setCity("Lagos");
        userRegisterRequest.setPostalCode("9410");
        userRegisterRequest.setCountry("Nigeria");
        userRegisterRequest.setStreetNumber("17");
        userRegisterRequest.setLgaName("Bariga");

        assertThrows(InvalidEmailException.class,()->userServiceImpl.registerUser(userRegisterRequest));
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
        userRegisterRequest.setCity("Lagos");
        userRegisterRequest.setPostalCode("9410");
        userRegisterRequest.setCountry("Nigeria");
        userRegisterRequest.setStreetNumber("17");
        userRegisterRequest.setLgaName("Bariga");
        userServiceImpl.registerUser(userRegisterRequest);


        UserRegisterRequest userRegisterRequest1 = new UserRegisterRequest();
        userRegisterRequest1.setUsername("Osundu");
        userRegisterRequest1.setPassword("password");
        userRegisterRequest1.setEmail("jiggydem@gmail.com");
        userRegisterRequest1.setFirstName("Benjamin");
        userRegisterRequest1.setLastName("Jacob");
        userRegisterRequest1.setPhoneNumber("1234567890");
        userRegisterRequest1.setCity("Lagos");
        userRegisterRequest1.setPostalCode("9410");
        userRegisterRequest1.setCountry("Nigeria");
        userRegisterRequest1.setStreetNumber("17");
        userRegisterRequest1.setLgaName("Bariga");

        assertThrows(DuplicateUsernameException.class,()->userServiceImpl.registerUser(userRegisterRequest1));
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
        userRegisterRequest.setCity("Lagos");
        userRegisterRequest.setPostalCode("9410");
        userRegisterRequest.setCountry("Nigeria");
        userRegisterRequest.setStreetNumber("17");
        userRegisterRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest);
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
        userRegisterRequest.setCity("Lagos");
        userRegisterRequest.setPostalCode("9410");
        userRegisterRequest.setCountry("Nigeria");
        userRegisterRequest.setStreetNumber("17");
        userRegisterRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest);
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
        userRegisterRequest.setCity("Lagos");
        userRegisterRequest.setPostalCode("9410");
        userRegisterRequest.setCountry("Nigeria");
        userRegisterRequest.setStreetNumber("17");
        userRegisterRequest.setLgaName("Bariga");


        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());

        FindProductRequest findProductRequest = new FindProductRequest();
        findProductRequest.setProductName("Yam");
        Product foundProduct = userServiceImpl.findProduct(findProductRequest);
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
        userRegisterRequest.setCity("Lagos");
        userRegisterRequest.setPostalCode("9410");
        userRegisterRequest.setCountry("Nigeria");
        userRegisterRequest.setStreetNumber("17");
        userRegisterRequest.setLgaName("Bariga");


        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());
        FindProductRequest findProductRequest = new FindProductRequest();
        findProductRequest.setProductName("Garri");
        assertThrows(NoProductFoundException.class,()->userServiceImpl.findProduct(findProductRequest));
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
        userRegisterRequest.setCity("Lagos");
        userRegisterRequest.setPostalCode("9410");
        userRegisterRequest.setCountry("Nigeria");
        userRegisterRequest.setStreetNumber("17");
        userRegisterRequest.setLgaName("Bariga");


        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());


        FindCategoryRequest findCategoryRequest = new FindCategoryRequest();
        findCategoryRequest.setCategoryName("Food");
        List<Product> foundProduct = userServiceImpl.findProductsByCategoryName(findCategoryRequest);
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
        userRegisterRequest.setCity("Lagos");
        userRegisterRequest.setPostalCode("9410");
        userRegisterRequest.setCountry("Nigeria");
        userRegisterRequest.setStreetNumber("17");
        userRegisterRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest);
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
        userRegisterRequest.setCity("Lagos");
        userRegisterRequest.setPostalCode("9410");
        userRegisterRequest.setCountry("Nigeria");
        userRegisterRequest.setStreetNumber("17");
        userRegisterRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest);
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
        Cart cartResponse = userServiceImpl.addProductToCart(cartRequests);
        assertEquals(3,cartResponse.getOrderItem().size());
    }

    @Test
    public void testUserCanCheckOut(){
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
        userRegisterRequest.setCity("Lagos");
        userRegisterRequest.setPostalCode("9410");
        userRegisterRequest.setCountry("Nigeria");
        userRegisterRequest.setStreetNumber("17");
        userRegisterRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest);
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
        Cart cartResponse = userServiceImpl.addProductToCart(cartRequests);
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
        userRegisterRequest.setCity("Lagos");
        userRegisterRequest.setPostalCode("9410");
        userRegisterRequest.setCountry("Nigeria");
        userRegisterRequest.setStreetNumber("17");
        userRegisterRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());

        AddToCartRequest cartRequest1 = new AddToCartRequest();
        cartRequest1.setProductName("Yam");
        cartRequest1.setQuantity(2);
        cartRequest1.setUserId(registerResponse.getUserId());

        AddToCartRequest cartRequest2 = new AddToCartRequest();
        cartRequest2.setProductName("Grand pure soya oil");
        cartRequest2.setQuantity(1);
        cartRequest2.setUserId(registerResponse.getUserId());

        List<AddToCartRequest> cartRequests = List.of(cartRequest1,cartRequest2);
        Cart cartResponse = userServiceImpl.addProductToCart(cartRequests);
        assertEquals(2,cartResponse.getOrderItem().size());

        FindProductRequest findProductRequest = new FindProductRequest();
        findProductRequest.setProductName("Grand pure soya oil");
        List<Cart> newCartList = userServiceImpl.removeProductFromCartByProductName(findProductRequest);
        Cart firstCart = newCartList.get(0);
        assertEquals(1,firstCart.getOrderItem().size());

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
        userRegisterRequest.setCity("Lagos");
        userRegisterRequest.setPostalCode("9410");
        userRegisterRequest.setCountry("Nigeria");
        userRegisterRequest.setStreetNumber("17");
        userRegisterRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());

        AddToCartRequest cartRequest1 = new AddToCartRequest();
        cartRequest1.setProductName("Yam");
        cartRequest1.setQuantity(10);
        cartRequest1.setUserId(registerResponse.getUserId());

        AddToCartRequest cartRequest2 = new AddToCartRequest();
        cartRequest2.setProductName("Grand pure soya oil");
        cartRequest2.setQuantity(1);
        cartRequest2.setUserId(registerResponse.getUserId());

        List<AddToCartRequest> cartRequests = List.of(cartRequest1,cartRequest2);
        Cart cartResponse = userServiceImpl.addProductToCart(cartRequests);
        assertEquals(2,cartResponse.getOrderItem().size());

        Order checkOutOrder = checkOutServiceImpl.checkOut(registerResponse.getUserId());
        assertEquals(checkOutOrder.getOrderStatus(), OrderStatus.COMPLETED_USER);


        ProductReviewRequest productReviewRequest = new ProductReviewRequest();
        productReviewRequest.setProductName("Yam");
        productReviewRequest.setUserId(registerResponse.getUserId());
        productReviewRequest.setMessage("Nice Product");
        productReviewRequest.setRating(Rating.FOUR);

        ProductReviewResponse productResponse = userServiceImpl.productReview(productReviewRequest,checkOutOrder.getId(),cartResponse.getId());
        assertEquals("Review successfully added",productResponse.getMessage());

    }

    @Test
    public void testUserCannotLeaveMoreThanOneReviewOnAProductPerOrder(){
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
        userRegisterRequest.setCity("Lagos");
        userRegisterRequest.setPostalCode("9410");
        userRegisterRequest.setCountry("Nigeria");
        userRegisterRequest.setStreetNumber("17");
        userRegisterRequest.setLgaName("Bariga");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest);
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
        Cart cartResponse = userServiceImpl.addProductToCart(cartRequests);
        assertEquals(3,cartResponse.getOrderItem().size());
        Order checkOutOrder = checkOutServiceImpl.checkOut(registerResponse.getUserId());
        assertEquals(checkOutOrder.getOrderStatus(), OrderStatus.COMPLETED_USER);

        ProductReviewRequest productReviewRequest = new ProductReviewRequest();
        productReviewRequest.setProductName("Yam");
        productReviewRequest.setUserId(registerResponse.getUserId());
        productReviewRequest.setMessage("Nice Product");
        productReviewRequest.setRating(Rating.FOUR);

        ProductReviewResponse productResponse = userServiceImpl.productReview(productReviewRequest,checkOutOrder.getId(),cartResponse.getId());
        assertEquals("Review successfully added",productResponse.getMessage());

        assertThrows(ExceededNumberOfReviewPerOrderException.class,()->userServiceImpl.productReview(productReviewRequest,checkOutOrder.getId(),cartResponse.getId()));
    }
}