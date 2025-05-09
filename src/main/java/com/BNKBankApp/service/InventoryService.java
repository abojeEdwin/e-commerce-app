package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Inventory;
import com.BNKBankApp.dto.request.CreateCategoryRequest;
import com.BNKBankApp.dto.resonse.CreatedInventoryResponse;

import java.util.List;

public interface InventoryService {
    CreatedInventoryResponse addProductToInventory(CreateCategoryRequest request);
    Inventory findProductById(String productId);
    void deleteAllInventory();
    List<Inventory> getAllInventory();

}
