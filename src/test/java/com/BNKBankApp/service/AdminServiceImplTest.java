package com.BNKBankApp.service;
import com.BNKBankApp.data.model.Admin;
import com.BNKBankApp.data.model.Role;
import com.BNKBankApp.dto.request.AdminRegisterRequest;
import com.BNKBankApp.dto.resonse.AdminRegisterResponse;
import com.BNKBankApp.exception.InvalidEmailException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    AdminServiceImpl adminServiceImpl;

    @BeforeEach
    void setUp() {
        adminServiceImpl.deleteAll();
    }

    @AfterEach
    void tearDown(){
        adminServiceImpl.deleteAll();
    }

    @Test
    public void testAdminCanRegister(){
        AdminRegisterRequest adminRegisterRequest = new AdminRegisterRequest();
        adminRegisterRequest.setUsername("Choko");
        adminRegisterRequest.setPassword("password");
        adminRegisterRequest.setEmail("newAdmin@gmail.com");
        adminRegisterRequest.setRole(Role.Admin);
        AdminRegisterResponse adminRegisterResponse = adminServiceImpl.registerAdmin(adminRegisterRequest);
        assertNotNull(adminRegisterResponse);
        assertEquals("Success",adminRegisterResponse.getStatus());
    }

    @Test
    public void testAdminRegisterWithWrongEmail(){
        AdminRegisterRequest adminRegisterRequest = new AdminRegisterRequest();
        adminRegisterRequest.setUsername("Choko");
        adminRegisterRequest.setPassword("password");
        adminRegisterRequest.setEmail(" ");
        adminRegisterRequest.setRole(Role.Admin);
        assertThrows(InvalidEmailException.class,()-> adminServiceImpl.registerAdmin(adminRegisterRequest));
    }

}