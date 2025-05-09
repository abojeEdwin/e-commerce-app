package com.BNKBankApp.service;
import com.BNKBankApp.data.model.Admin;
import com.BNKBankApp.data.model.Role;
import com.BNKBankApp.data.repository.AdminRepo;
import com.BNKBankApp.data.repository.CategoryRepo;
import com.BNKBankApp.dto.request.AddProductRequest;
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
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @BeforeEach
    void setUp() {
        adminServiceImpl.deleteAll();
        categoryServiceImpl.deleteAll();
        productServiceImpl.deleteAllProducts();
    }

    @AfterEach
    void tearDown(){
        adminServiceImpl.deleteAll();
        categoryServiceImpl.deleteAll();
        productServiceImpl.deleteAllProducts();
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

    @Test
    public void testAdminCanFindCategoryByName(){
        CreateCategoryRequest createdCategoryRequest = new CreateCategoryRequest();
        createdCategoryRequest.setName("Electric Appliances");
        createdCategoryRequest.setDescription("Electric Appliances Category");
        adminServiceImpl.createCategory(createdCategoryRequest);
        assertEquals(1,categoryRepo.count());
        assertEquals("Electric Appliances Category", categoryServiceImpl.findByName("Electric Appliances").getDescription());
    }

    @Test
    public void testAdminCanAddProductsToCategory(){
        CreateCategoryRequest createdCategoryRequest = new CreateCategoryRequest();
        createdCategoryRequest.setName("Electric Appliances");
        createdCategoryRequest.setDescription("Electric Appliances Category");
        CreatedCategoryResponse  response = adminServiceImpl.createCategory(createdCategoryRequest);

        CreateCategoryRequest createdCategoryRequest2 = new CreateCategoryRequest();
        createdCategoryRequest2.setName("Food Stuff");
        createdCategoryRequest2.setDescription("Food Stuff Category");
        CreatedCategoryResponse response1 =  adminServiceImpl.createCategory(createdCategoryRequest2);

        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setCategoryId(response1.getId());
        addProductRequest.setDescription("Coco Yam");
        addProductRequest.setName("Yam");
        addProductRequest.setPrice(1000.0);
        addProductRequest.setQuantity(10);
        adminServiceImpl.addProduct(addProductRequest);

        AddProductRequest addProductRequest2 = new AddProductRequest();
        addProductRequest2.setCategoryId(response1.getId());
        addProductRequest2.setDescription("Vegetable Oil");
        addProductRequest2.setName("Grand pure soya oil");
        addProductRequest2.setPrice(1200.0);
        addProductRequest2.setQuantity(10);
        adminServiceImpl.addProduct(addProductRequest2);


        assertEquals(2,productServiceImpl.count());
    }



}