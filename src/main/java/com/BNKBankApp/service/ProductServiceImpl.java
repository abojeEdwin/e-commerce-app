package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Product;
import com.BNKBankApp.data.repository.ProductRepo;
import com.BNKBankApp.dto.request.AddProductRequest;
import com.BNKBankApp.dto.request.CreateInventoryRequest;
import com.BNKBankApp.dto.request.ProductReviewRequest;
import com.BNKBankApp.dto.resonse.AddedProductResponse;
import com.BNKBankApp.dto.resonse.AllProductsInACategoryResponse;
import com.BNKBankApp.dto.resonse.CreatedInventoryResponse;
import com.BNKBankApp.dto.resonse.ProductReviewResponse;
import com.BNKBankApp.exception.ProductAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private InventoryServiceImpl inventoryServiceImpl;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public AddedProductResponse addProduct(AddProductRequest addProductRequest) {
        Product foundProduct = productRepo.findProductByName(addProductRequest.getName());
        if (foundProduct == null) {
            Product product = new Product();
            product.setName(addProductRequest.getName());
            product.setPrice(addProductRequest.getPrice());
            product.setDescription(addProductRequest.getDescription());
            product.setCategoryId(addProductRequest.getCategoryId());
            product.setImageUrl(addProductRequest.getImageUrl());
            product.setQuantity(addProductRequest.getQuantity());
            productRepo.save(product);

            CreateInventoryRequest createInventoryRequest = new CreateInventoryRequest();
            createInventoryRequest.setStockQuantity(addProductRequest.getQuantity());
            createInventoryRequest.setProductId(product.getId());
            createInventoryRequest.setLastRestockDate(Date.from(Instant.now()));
            CreatedInventoryResponse createdInventoryResponse = inventoryServiceImpl.addProductToInventory(createInventoryRequest);

            AddedProductResponse response = new AddedProductResponse();
            response.setCategory(product.getCategoryId());
            response.setDescription(product.getDescription());
            response.setImageUrl(product.getImageUrl());
            response.setQuantity(product.getQuantity());
            response.setName(product.getName());
            response.setPrice(product.getPrice());
            response.setStatus("Success");
            return response;
        }
        throw new ProductAlreadyExistException("Product already exists");
    }

    @Override
    public void deleteAllProducts() {
        productRepo.deleteAll();
    }

    @Override
    public AllProductsInACategoryResponse getAllProductsInACategory(String category) {
        return null;
    }

    @Override
    public void removeProductByName(String productName) {

    }

    @Override
    public Product findProduct(String productName) {
        return null;
    }

    @Override
    public List<Product> findAllProducts() {
        return List.of();
    }

    @Override
    public List<Product> findProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public ProductReviewResponse addReview(ProductReviewRequest productReviewRequest) {
        return null;
    }

    @Override
    public long count() {
        return productRepo.count();
    }
}
