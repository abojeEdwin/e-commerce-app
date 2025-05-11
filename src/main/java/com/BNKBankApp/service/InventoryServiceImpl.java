package com.BNKBankApp.service;
import com.BNKBankApp.data.model.Inventory;
import com.BNKBankApp.data.repository.InventoryRepo;
import com.BNKBankApp.dto.request.CreateCategoryRequest;
import com.BNKBankApp.dto.request.CreateInventoryRequest;
import com.BNKBankApp.dto.resonse.CreatedInventoryResponse;
import com.BNKBankApp.exception.NoProductFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;


    @Override
    public CreatedInventoryResponse addProductToInventory(CreateInventoryRequest request) {
        Inventory inventory = new Inventory();
        inventory.setProductId(request.getProductId());
        inventory.setStockQuantity(request.getStockQuantity());
        inventory.setLastRestockedDate(request.getLastRestockDate());
        inventoryRepo.save(inventory);

        CreatedInventoryResponse createdInventoryResponse = new CreatedInventoryResponse();
        createdInventoryResponse.setProductId(request.getProductId());
        createdInventoryResponse.setLastRestockedDate(inventory.getLastRestockedDate());
        createdInventoryResponse.setStockQuantity(inventory.getStockQuantity());
        createdInventoryResponse.setStatus("Success");
        return createdInventoryResponse;

    }

    @Override
    public Inventory findProductById(String productId) {
        Inventory foundInventory = inventoryRepo.findByProductId(productId);
        if (foundInventory == null) {
            throw new NoProductFoundException("Product not found");
        }
        return foundInventory;
    }

    @Override
    public void deleteAllInventory() {
        inventoryRepo.deleteAll();
    }

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepo.findAll();
    }
}
