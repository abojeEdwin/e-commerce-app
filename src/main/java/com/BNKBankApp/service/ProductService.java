package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Cart;
import com.BNKBankApp.data.model.Product;
import com.BNKBankApp.dto.request.AddProductRequest;
import com.BNKBankApp.dto.request.ProductReviewRequest;
import com.BNKBankApp.dto.resonse.AddedProductResponse;
import com.BNKBankApp.dto.resonse.AllProductsInACategoryResponse;
import com.BNKBankApp.dto.resonse.ProductReviewResponse;

import java.util.List;

public interface ProductService {
    AddedProductResponse addProduct(AddProductRequest addProductRequest);
    void deleteAllProducts();
    AllProductsInACategoryResponse getAllProductsInACategory(String category);
    void removeProductByName(String productName);
    Product findProduct(String productName);
    List<Product> findAllProducts();
    List<Product> findProductsByCategory(String category);
    long count();
}
