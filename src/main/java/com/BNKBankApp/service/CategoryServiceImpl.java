package com.BNKBankApp.service;

import com.BNKBankApp.data.model.Category;
import com.BNKBankApp.data.repository.CategoryRepo;
import com.BNKBankApp.dto.request.CreateCategoryRequest;
import com.BNKBankApp.dto.resonse.CreatedCategoryResponse;
import com.BNKBankApp.exception.CategoryAlreadyExistException;
import com.BNKBankApp.exception.NoSuchCategoryFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public CreatedCategoryResponse createCategory(CreateCategoryRequest createCategoryRequest) {
        Category foundCategory = categoryRepo.findCategoryByName(createCategoryRequest.getName());
        if(foundCategory == null){
            Category category = new Category();
            category.setName(createCategoryRequest.getName());
            category.setDescription(createCategoryRequest.getDescription());
            categoryRepo.save(category);

            CreatedCategoryResponse response = new CreatedCategoryResponse();
            response.setDescription(createCategoryRequest.getDescription());
            response.setName(createCategoryRequest.getName());
            return response;
        }
        throw new CategoryAlreadyExistException("Category Already Exist");
    }

    @Override
    public void deleteAll() {
        categoryRepo.deleteAll();
    }

    @Override
    public Category findByName(String category) {
        Category foundCategory = categoryRepo.findCategoryByName(category);
        if(foundCategory == null){
            throw new NoSuchCategoryFoundException("No Such Category Found");
        }
        return foundCategory;
    }
}
