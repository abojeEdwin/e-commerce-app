package com.BNKBankApp.service;
import com.BNKBankApp.data.model.Admin;
import com.BNKBankApp.data.model.Inventory;
import com.BNKBankApp.data.model.Product;
import com.BNKBankApp.data.model.Role;
import com.BNKBankApp.data.repository.AdminRepo;
import com.BNKBankApp.data.repository.CategoryRepo;
import com.BNKBankApp.dto.request.AddProductRequest;
import com.BNKBankApp.dto.request.AdminRegisterRequest;
import com.BNKBankApp.dto.request.CreateCategoryRequest;
import com.BNKBankApp.dto.resonse.AddedProductResponse;
import com.BNKBankApp.dto.resonse.AdminRegisterResponse;
import com.BNKBankApp.dto.resonse.AllProductsInACategoryResponse;
import com.BNKBankApp.dto.resonse.CreatedCategoryResponse;
import com.BNKBankApp.exception.DuplicateEmailException;
import com.BNKBankApp.exception.DuplicateUsernameException;
import com.BNKBankApp.exception.InvalidEmailException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Autowired
    private InventoryServiceImpl inventoryServiceImpl;

    @BeforeEach
    void setUp() {
        adminServiceImpl.deleteAll();
        categoryServiceImpl.deleteAll();
        productServiceImpl.deleteAllProducts();
        inventoryServiceImpl.deleteAllInventory();
    }

    @AfterEach
    void tearDown(){
        adminServiceImpl.deleteAll();
        categoryServiceImpl.deleteAll();
        productServiceImpl.deleteAllProducts();
        inventoryServiceImpl.deleteAllInventory();
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
        assertEquals(1,categoryServiceImpl.count());
    }

    @Test
    public void testAdminCanFindCategoryByName(){
        CreateCategoryRequest createdCategoryRequest = new CreateCategoryRequest();
        createdCategoryRequest.setName("Electric Appliances");
        createdCategoryRequest.setDescription("Electric Appliances Category");
        adminServiceImpl.createCategory(createdCategoryRequest);
        assertEquals(1,categoryServiceImpl.count());
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

    @Test
    public void testAdminCanRemoveProductByName(){
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

        adminServiceImpl.removeProduct("Grand pure soya oil");
        assertEquals(  1,productServiceImpl.count());

    }

    @Test
    public void testAdminCanFindProductsByCategory(){
        CreateCategoryRequest createdCategoryRequest = new CreateCategoryRequest();
        createdCategoryRequest.setName("Electric Appliances");
        createdCategoryRequest.setDescription("Electric Appliances Category");
        CreatedCategoryResponse  response = adminServiceImpl.createCategory(createdCategoryRequest);

        CreateCategoryRequest createdCategoryRequest2 = new CreateCategoryRequest();
        createdCategoryRequest2.setName("Food");
        createdCategoryRequest2.setDescription("Food Stuff Category");
        CreatedCategoryResponse response1 =  adminServiceImpl.createCategory(createdCategoryRequest2);
        assertEquals("Food",response1.getName());

        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setCategoryId(response1.getId());
        addProductRequest.setDescription("Coco Yam");
        addProductRequest.setName("Yam");
        addProductRequest.setPrice(1000.0);
        addProductRequest.setQuantity(10);
        AddedProductResponse productResponse = adminServiceImpl.addProduct(addProductRequest);
        assertEquals("Success",productResponse.getStatus());
        assertEquals(response1.getId(),productResponse.getCategoryId());

        AddProductRequest addProductRequest2 = new AddProductRequest();
        addProductRequest2.setCategoryId(response1.getId());
        addProductRequest2.setDescription("Vegetable Oil");
        addProductRequest2.setName("Grand pure soya oil");
        addProductRequest2.setPrice(1200.0);
        addProductRequest2.setQuantity(10);
        AddedProductResponse productResponse2 = adminServiceImpl.addProduct(addProductRequest2);

        AllProductsInACategoryResponse allProductsInACategoryResponse = adminServiceImpl.getAllProductsInACategory("Food");
        assertEquals(2,allProductsInACategoryResponse.getTotal());
    }

    @Test
    public void testAdminCanGetAProductInInventory(){
        CreateCategoryRequest createdCategoryRequest = new CreateCategoryRequest();
        createdCategoryRequest.setName("Electric Appliances");
        createdCategoryRequest.setDescription("Electric Appliances Category");
        CreatedCategoryResponse  response = adminServiceImpl.createCategory(createdCategoryRequest);

        CreateCategoryRequest createdCategoryRequest2 = new CreateCategoryRequest();
        createdCategoryRequest2.setName("Food");
        createdCategoryRequest2.setDescription("Food Stuff Category");
        CreatedCategoryResponse response1 =  adminServiceImpl.createCategory(createdCategoryRequest2);
        assertEquals("Food",response1.getName());

        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setCategoryId(response1.getId());
        addProductRequest.setDescription("Coco Yam");
        addProductRequest.setName("Yam");
        addProductRequest.setPrice(1000.0);
        addProductRequest.setQuantity(10);
        AddedProductResponse productResponse = adminServiceImpl.addProduct(addProductRequest);
        assertEquals("Success",productResponse.getStatus());
        assertEquals(response1.getId(),productResponse.getCategoryId());

        AddProductRequest addProductRequest2 = new AddProductRequest();
        addProductRequest2.setCategoryId(response1.getId());
        addProductRequest2.setDescription("Vegetable Oil");
        addProductRequest2.setName("Grand pure soya oil");
        addProductRequest2.setPrice(1200.0);
        addProductRequest2.setQuantity(10);
        AddedProductResponse productResponse2 = adminServiceImpl.addProduct(addProductRequest2);

        Inventory productInInventoryResponse = adminServiceImpl.findByProductId(productResponse2.getId());
        assertEquals(productInInventoryResponse.getProductId(),productResponse2.getId());
    }

    @Test
    public void testAdminCanGetAllProductsInInventory(){
        CreateCategoryRequest createdCategoryRequest = new CreateCategoryRequest();
        createdCategoryRequest.setName("Electric Appliances");
        createdCategoryRequest.setDescription("Electric Appliances Category");
        CreatedCategoryResponse  response = adminServiceImpl.createCategory(createdCategoryRequest);

        CreateCategoryRequest createdCategoryRequest2 = new CreateCategoryRequest();
        createdCategoryRequest2.setName("Food");
        createdCategoryRequest2.setDescription("Food Stuff Category");
        CreatedCategoryResponse response1 =  adminServiceImpl.createCategory(createdCategoryRequest2);
        assertEquals("Food",response1.getName());

        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setCategoryId(response1.getId());
        addProductRequest.setDescription("Coco Yam");
        addProductRequest.setName("Yam");
        addProductRequest.setPrice(1000.0);
        addProductRequest.setQuantity(10);
        AddedProductResponse productResponse = adminServiceImpl.addProduct(addProductRequest);
        assertEquals("Success",productResponse.getStatus());
        assertEquals(response1.getId(),productResponse.getCategoryId());

        AddProductRequest addProductRequest2 = new AddProductRequest();
        addProductRequest2.setCategoryId(response1.getId());
        addProductRequest2.setDescription("Vegetable Oil");
        addProductRequest2.setName("Grand pure soya oil");
        addProductRequest2.setPrice(1200.0);
        addProductRequest2.setQuantity(10);
        AddedProductResponse productResponse2 = adminServiceImpl.addProduct(addProductRequest2);

        List<Inventory> productsInInventory = adminServiceImpl.getAllInventory();
        assertEquals(2,productsInInventory.size());

    }

    @Test
    public void testAdminCanCheckListOfOrders(){

    }

}