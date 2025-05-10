package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Category;
import com.BNKBankApp.dto.request.CreateCategoryRequest;
import com.BNKBankApp.dto.resonse.CreatedCategoryResponse;

public interface CategoryService {
    CreatedCategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);
    void deleteAll();
    Category findByName(String category);
    long count();
}
