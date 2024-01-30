package com.example.demo.service;

import com.example.models.Category;
import com.example.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category data) {
        return categoryRepository.save(data);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
        return category;
    }

    public Category updateCategory(Long id, Category data) {
        Category category = getCategoryById(id);
        category.setName(data.getName());
        category.setDescription(data.getDescription());
        return categoryRepository.save(category);
    }
}