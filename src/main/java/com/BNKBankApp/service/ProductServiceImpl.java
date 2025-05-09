package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Product;
import com.BNKBankApp.dto.request.AddProductRequest;
import com.BNKBankApp.dto.request.ProductReviewRequest;
import com.BNKBankApp.dto.resonse.AddedProductResponse;
import com.BNKBankApp.dto.resonse.AllProductsInACategoryResponse;
import com.BNKBankApp.dto.resonse.ProductReviewResponse;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public AddedProductResponse addProduct(AddProductRequest addProductRequest) {
        return null;
    }

    @Override
    public void deleteAllProducts() {

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
}
