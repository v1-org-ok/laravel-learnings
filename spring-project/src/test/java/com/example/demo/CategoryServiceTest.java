package com.example.demo.service;

import com.example.models.Category;
import com.example.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    void getAllCategories_ShouldReturnAllCategories() {
        // Arrange
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Category 1", "Description 1"));
        categories.add(new Category(2L, "Category 2", "Description 2"));
        when(categoryRepository.findAll()).thenReturn(categories);

        // Act
        List<Category> result = categoryService.getAllCategories();

        // Assert
        assertEquals(categories, result);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void createCategory_ShouldReturnCreatedCategory() {
        // Arrange
        Category category = new Category(1L, "Category 1", "Description 1");
        when(categoryRepository.save(category)).thenReturn(category);

        // Act
        Category result = categoryService.createCategory(category);

        // Assert
        assertEquals(category, result);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void getCategoryById_WithExistingId_ShouldReturnCategory() {
        // Arrange
        Long id = 1L;
        Category category = new Category(id, "Category 1", "Description 1");
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));

        // Act
        Category result = categoryService.getCategoryById(id);

        // Assert
        assertEquals(category, result);
        verify(categoryRepository, times(1)).findById(id);
    }

    @Test
    void getCategoryById_WithNonExistingId_ShouldThrowException() {
        // Arrange
        Long id = 1L;
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> categoryService.getCategoryById(id));
        verify(categoryRepository, times(1)).findById(id);
    }

    @Test
    void deleteCategory_WithExistingId_ShouldReturnDeletedCategory() {
        // Arrange
        Long id = 1L;
        Category category = new Category(id, "Category 1", "Description 1");
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));

        // Act
        Category result = categoryService.deleteCategory(id);

        // Assert
        assertEquals(category, result);
        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, times(1)).delete(category);
    }

    @Test
    void deleteCategory_WithNonExistingId_ShouldThrowException() {
        // Arrange
        Long id = 1L;
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> categoryService.deleteCategory(id));
        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, never()).delete(any());
    }

    @Test
    void updateCategory_WithExistingId_ShouldReturnUpdatedCategory() {
        // Arrange
        Long id = 1L;
        Category existingCategory = new Category(id, "Category 1", "Description 1");
        Category updatedCategory = new Category(id, "Updated Category", "Updated Description");
        when(categoryRepository.findById(id)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(existingCategory)).thenReturn(updatedCategory);

        // Act
        Category result = categoryService.updateCategory(id, updatedCategory);

        // Assert
        assertEquals(updatedCategory, result);
        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, times(1)).save(existingCategory);
    }

    @Test
    void updateCategory_WithNonExistingId_ShouldThrowException() {
        // Arrange
        Long id = 1L;
        Category updatedCategory = new Category(id, "Updated Category", "Updated Description");
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> categoryService.updateCategory(id, updatedCategory));
        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, never()).save(any());
    }
}
