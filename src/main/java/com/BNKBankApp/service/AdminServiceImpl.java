package com.BNKBankApp.service;
import com.BNKBankApp.data.model.*;
import com.BNKBankApp.data.repository.AddressRepo;
import com.BNKBankApp.data.repository.AdminRepo;
import com.BNKBankApp.data.repository.CategoryRepo;
import com.BNKBankApp.dto.request.AddProductRequest;
import com.BNKBankApp.dto.request.AdminRegisterRequest;
import com.BNKBankApp.dto.request.CreateCategoryRequest;
import com.BNKBankApp.dto.request.FindProductRequest;
import com.BNKBankApp.dto.resonse.*;
import com.BNKBankApp.exception.*;
import com.BNKBankApp.util.VerifyEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.BNKBankApp.util.Mapper.map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AddressRepo addressRepo;
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private VerifyEmail verifyEmail;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private InventoryServiceImpl inventoryServiceImpl;
    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Override
    public AdminRegisterResponse registerAdmin(AdminRegisterRequest registerAdminRequest) {
        Admin foundAdmin = adminRepo.findByEmail(registerAdminRequest.getEmail());
        if(foundAdmin == null) {
            Admin admin = map(registerAdminRequest);
            adminRepo.save(admin);
        }
        if(!verifyEmail.isVerifiedEmail(registerAdminRequest.getEmail())) {throw new InvalidEmailException("Invalid email, Please try again");}
        if(adminRepo.existsByEmail(registerAdminRequest.getEmail())) {throw new DuplicateEmailException("Email already exists");}
        if(adminRepo.existsByUsername(registerAdminRequest.getUsername())) {throw new DuplicateUsernameException("Username already exists");}
        AdminRegisterResponse adminRegisterResponse = new AdminRegisterResponse();
        adminRegisterResponse.setEmail(registerAdminRequest.getEmail());
        adminRegisterResponse.setUsername(registerAdminRequest.getUsername());
        adminRegisterResponse.setStatus("Success");
        adminRegisterResponse.setRole(Role.Admin);
        return adminRegisterResponse;
    }

    @Override
    public CreatedCategoryResponse createCategory(CreateCategoryRequest createCategoryRequest) {
        CreatedCategoryResponse response  = categoryService.createCategory(createCategoryRequest);
        return response;
    }

    @Override
    public AddedProductResponse addProduct(AddProductRequest addProductRequest) {
        return productServiceImpl.addProduct(addProductRequest);
    }

    @Override
    public AllProductsInACategoryResponse getAllProductsInACategory(String category) {
        return productServiceImpl.getAllProductsInACategory(category);
    }

    @Override
    public Inventory findByProductId(String Id) {
        return inventoryServiceImpl.findProductById(Id);
    }

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryServiceImpl.getAllInventory();
    }

    @Override
    public void removeProduct(FindProductRequest findProductRequest) {
        productServiceImpl.removeProductByName(findProductRequest.getProductName());
    }

    @Override
    public Product findProduct(FindProductRequest findProductRequest) {
        Product foundProduct = productServiceImpl.findProduct(findProductRequest.getProductName());
        if(foundProduct == null) {throw new NoProductFoundException("Product Not Found");}return foundProduct;}

    @Override
    public List<Order> checkListOfOrders() {
        return orderServiceImpl.checkListOfOrders();
    }

    @Override
    public ProcessOrderResponse processOrder(String orderId) {
        return orderServiceImpl.processOrder(orderId);
    }

}
