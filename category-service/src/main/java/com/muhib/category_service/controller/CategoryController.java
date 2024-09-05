package com.muhib.category_service.controller;

import com.muhib.category_service.entity.Category;
import com.muhib.category_service.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @PostMapping("/category/add")
    public Category saveCategory(@Validated @RequestBody Category category) {
        LOGGER.info("Inside saveCategory of CategoryController: category={}", category);
        return categoryService.saveCategory(category);
    }

    @GetMapping("/categories")
    public List<Category> fetchCategoryList(){
        LOGGER.info("Inside fetchCategoryList of CategoryController");
        return categoryService.fetchCategoryList();
    }
    @GetMapping("/category-name/{name}")
    public List<Category> fetchCategoryByName(@Validated @PathVariable String name){
        return categoryService.fetchCategoryByName(name);
    }

    @GetMapping("/category-id/{categoryId}")
    public List<Category> fetchCategoryById(@Validated @PathVariable Long categoryId){
        return categoryService.fetchCategoryById(categoryId);
    }
}
