package com.BNKBankApp.service;
import com.BNKBankApp.dto.request.AddressRequest;
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

    @BeforeEach
    void setUp() {
        userServiceImpl.deleteAll();
    }
    @AfterEach
    void tearDown() {
        userServiceImpl.deleteAll();
    }

    @Test
    public void testUserCanRegister() {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUsername("Osundu");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setEmail("abojeedwin@gmail");
        userRegisterRequest.setFirstName("Benjamin");
        userRegisterRequest.setLastName("Jacob");
        userRegisterRequest.setPhoneNumber("1234567890");

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setCity("Lagos");
        addressRequest.setPostalCode("9410");
        addressRequest.setCountry("Nigeria");
        addressRequest.setStreetNumber("17");
        addressRequest.setLgaName("Bariga");
        addressRequest.setUserId(" ");

        UserRegisterResponse registerResponse = userServiceImpl.registerUser(userRegisterRequest,addressRequest);
        assertEquals("Success",registerResponse.getStatus());
        assertEquals("Osundu",registerResponse.getUsername());
    }

}