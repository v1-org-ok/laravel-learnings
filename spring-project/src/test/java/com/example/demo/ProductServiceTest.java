package com.example.services;

import com.example.models.Product;
import com.example.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    void getAllProducts_ShouldReturnAllProducts() {
        // Arrange
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", 10.0));
        products.add(new Product(2L, "Product 2", 20.0));
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> result = productService.getAllProducts();

        // Assert
        assertEquals(products, result);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void createProduct_ShouldSaveProductAndReturnIt() {
        // Arrange
        Product product = new Product(1L, "Product 1", 10.0);
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product result = productService.createProduct(product);

        // Assert
        assertEquals(product, result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void getProductById_WithExistingId_ShouldReturnProduct() {
        // Arrange
        Long id = 1L;
        Product product = new Product(id, "Product 1", 10.0);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // Act
        Product result = productService.getProductById(id);

        // Assert
        assertEquals(product, result);
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void getProductById_WithNonExistingId_ShouldThrowException() {
        // Arrange
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> productService.getProductById(id));
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void deleteProduct_WithExistingId_ShouldDeleteAndReturnProduct() {
        // Arrange
        Long id = 1L;
        Product product = new Product(id, "Product 1", 10.0);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // Act
        Product result = productService.deleteProduct(id);

        // Assert
        assertEquals(product, result);
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void deleteProduct_WithNonExistingId_ShouldThrowException() {
        // Arrange
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> productService.deleteProduct(id));
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, never()).delete(any());
    }

    @Test
    void updateProduct_WithExistingId_ShouldUpdateAndReturnProduct() {
        // Arrange
        Long id = 1L;
        Product existingProduct = new Product(id, "Product 1", 10.0);
        Product updatedProduct = new Product(id, "Updated Product 1", 20.0);
        when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        // Act
        Product result = productService.updateProduct(id, updatedProduct);

        // Assert
        assertEquals(updatedProduct, result);
        assertEquals(updatedProduct.getName(), existingProduct.getName());
        assertEquals(updatedProduct.getPrice(), existingProduct.getPrice());
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    void updateProduct_WithNonExistingId_ShouldThrowException() {
        // Arrange
        Long id = 1L;
        Product updatedProduct = new Product(id, "Updated Product 1", 20.0);
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> productService.updateProduct(id, updatedProduct));
        verify(productRepository, times(1)).findById(id);
        verify(productRepository, never()).save(any());
    }
}
