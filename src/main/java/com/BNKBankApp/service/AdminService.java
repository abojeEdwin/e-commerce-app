package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Admin;
import com.BNKBankApp.data.model.Inventory;
import com.BNKBankApp.data.model.Order;
import com.BNKBankApp.data.model.Product;
import com.BNKBankApp.dto.request.AddProductRequest;
import com.BNKBankApp.dto.request.AdminRegisterRequest;
import com.BNKBankApp.dto.request.CreateCategoryRequest;
import com.BNKBankApp.dto.resonse.*;
import java.util.List;

public interface AdminService {
    AdminRegisterResponse registerAdmin(AdminRegisterRequest registerAdminRequest);
     CreatedCategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);
     AddedProductResponse addProduct(AddProductRequest addProductRequest);
     AllProductsInACategoryResponse getAllProductsInACategory(String category);
     Inventory findByProductId(String Id);
     List<Inventory> getAllInventory();
     void removeProduct(String productName);
     Product findProduct(String name);
     List <Order> checkListOfOrders();
     ProcessOrderResponse processOrder(String orderId);
}
