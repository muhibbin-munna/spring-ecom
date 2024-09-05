package com.muhib.category_service.service;


import com.muhib.category_service.controller.CategoryController;
import com.muhib.category_service.entity.Category;
import com.muhib.category_service.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }


    public List<Category> fetchCategoryByName(String name) {
        return categoryRepository.findByCategoryNameContainingIgnoreCase(name);
    }

    public List<Category> fetchCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(Collections::singletonList)
                .orElse(Collections.emptyList());
    }

    public List<Category> fetchCategoryList() {
        return categoryRepository.findAll();
    }

}
