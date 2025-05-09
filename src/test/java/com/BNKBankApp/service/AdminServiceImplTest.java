package com.BNKBankApp.service;
import com.BNKBankApp.data.model.Admin;
import com.BNKBankApp.data.model.Role;
import com.BNKBankApp.data.repository.AdminRepo;
import com.BNKBankApp.data.repository.CategoryRepo;
import com.BNKBankApp.dto.request.AdminRegisterRequest;
import com.BNKBankApp.dto.request.CreateCategoryRequest;
import com.BNKBankApp.dto.resonse.AdminRegisterResponse;
import com.BNKBankApp.dto.resonse.CreatedCategoryResponse;
import com.BNKBankApp.exception.DuplicateEmailException;
import com.BNKBankApp.exception.DuplicateUsernameException;
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
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @BeforeEach
    void setUp() {
        adminServiceImpl.deleteAll();
        adminRepo.deleteAll();

    }

    @AfterEach
    void tearDown(){
        adminServiceImpl.deleteAll();
        adminRepo.deleteAll();
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

    @Test
    public void testAdminWthDuplicateEmail(){
        AdminRegisterRequest adminRegisterRequest = new AdminRegisterRequest();
        adminRegisterRequest.setUsername("Choko");
        adminRegisterRequest.setPassword("password");
        adminRegisterRequest.setEmail("newAdmin@gmail.com");
        adminRegisterRequest.setRole(Role.Admin);
        assertThrows(DuplicateEmailException.class,()->adminServiceImpl.registerAdmin(adminRegisterRequest));
    }

    @Test
    public void testAdminRegisterWithDuplicateUsername(){
        AdminRegisterRequest adminRegisterRequest = new AdminRegisterRequest();
        adminRegisterRequest.setUsername("Choko");
        adminRegisterRequest.setPassword("password");
        adminRegisterRequest.setEmail("newAdmin@gmail.com");
        adminRegisterRequest.setRole(Role.Admin);
        assertThrows(DuplicateUsernameException.class,()-> adminServiceImpl.registerAdmin(adminRegisterRequest));
    }

    @Test
    public void testAdminCanCreateACategory(){

        CreateCategoryRequest createdCategoryRequest = new CreateCategoryRequest();
        createdCategoryRequest.setName("Clothings");
        createdCategoryRequest.setDescription("Clothings Category");
        adminServiceImpl.createCategory(createdCategoryRequest);
        assertEquals(1,categoryRepo.count());
    }



}