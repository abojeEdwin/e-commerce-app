package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Category;
import com.BNKBankApp.dto.request.CreateCategoryRequest;
import com.BNKBankApp.dto.resonse.CreatedCategoryResponse;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public CreatedCategoryResponse createCategory(CreateCategoryRequest createCategoryRequest) {
        return null;
    }

    @Override
    public void deleteAllCategories() {

    }

    @Override
    public Category findByName(String category) {
        return null;
    }
}
