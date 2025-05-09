package com.BNKBankApp.service;
import com.BNKBankApp.data.model.Inventory;
import com.BNKBankApp.dto.request.CreateCategoryRequest;
import com.BNKBankApp.dto.resonse.CreatedInventoryResponse;
import java.util.List;


public class InventoryServiceImpl implements InventoryService {
    @Override
    public CreatedInventoryResponse addProductToInventory(CreateCategoryRequest request) {
        return null;
    }

    @Override
    public Inventory findProductById(String productId) {
        return null;
    }

    @Override
    public void deleteAllInventory() {

    }

    @Override
    public List<Inventory> getAllInventory() {
        return List.of();
    }
}
