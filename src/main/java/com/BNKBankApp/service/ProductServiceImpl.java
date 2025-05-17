package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Category;
import com.BNKBankApp.data.model.Product;
import com.BNKBankApp.data.repository.ProductRepo;
import com.BNKBankApp.dto.request.AddProductRequest;
import com.BNKBankApp.dto.request.CreateInventoryRequest;
import com.BNKBankApp.dto.request.ProductReviewRequest;
import com.BNKBankApp.dto.resonse.AddedProductResponse;
import com.BNKBankApp.dto.resonse.AllProductsInACategoryResponse;
import com.BNKBankApp.dto.resonse.CreatedInventoryResponse;
import com.BNKBankApp.dto.resonse.ProductReviewResponse;
import com.BNKBankApp.exception.NoProductFoundException;
import com.BNKBankApp.exception.ProductAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private InventoryServiceImpl inventoryServiceImpl;
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

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
            response.setCategoryId(product.getCategoryId());
            response.setDescription(product.getDescription());
            response.setImageUrl(product.getImageUrl());
            response.setQuantity(product.getQuantity());
            response.setName(product.getName());
            response.setPrice(product.getPrice());
            response.setStatus("Success");
            response.setId(product.getId());
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
        Category foundCategory = categoryServiceImpl.findByName(category);
        List<Product> product = productRepo.findByCategoryId(foundCategory.getId());
        AllProductsInACategoryResponse allProductsInACategoryResponse = new AllProductsInACategoryResponse();
        allProductsInACategoryResponse.setProductList(product);
        allProductsInACategoryResponse.setTotal(product.size());
        return allProductsInACategoryResponse;
    }

    @Override
    public void removeProductByName(String productName) {
        Product foundProduct = findProduct(productName);
        if(foundProduct == null){throw new NoProductFoundException("Product Does Not Exist");}productRepo.delete(foundProduct);}

    @Override
    public Product findProduct(String productName) {
        Product foundProduct = productRepo.findProductByName(productName);
        if (foundProduct == null) {throw new NoProductFoundException("No Product Found");}return foundProduct;}

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
