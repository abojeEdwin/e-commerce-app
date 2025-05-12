package com.BNKBankApp.service;
import com.BNKBankApp.data.model.Address;
import com.BNKBankApp.dto.request.UserRegisterRequest;
import com.BNKBankApp.dto.resonse.UserRegisterResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userServiceImpl;
//
//    @BeforeEach
//    void setUp() {
//        userServiceImpl.deleteAll();
//    }
//    @AfterEach
//    void tearDown() {
//        userServiceImpl.deleteAll();
//    }

    @Test
    public void testUserCanRegister() {
        Address address = new Address();
        address.setStreetName("Olatunji");
        address.setStreetNumber("12");
        address.setCity("Oakland");
        address.setLgaName("Bariga");
        address.setPostalCode("9410");
        address.setCountry("Nigeria");

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("Osundu");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setEmail("abojeedwin@gmail");
        userRegisterRequest.setFirstName("Benjamin");
        userRegisterRequest.setLastName("Jacob");
        userRegisterRequest.setPhoneNumber("1234567890");
        userRegisterRequest.setAddress(address);

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());
    }

}