package com.ing.storemanagement;

import com.ing.storemanagement.domain.Product;
import com.ing.storemanagement.repository.ProductRepository;
import com.ing.storemanagement.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductServiceImpl productServiceImpl;

    @BeforeEach
    public void setUp() {
        this.productServiceImpl = new ProductServiceImpl(productRepository);
    }

    @Test
    public void testGetProducts() {
        Mockito.when(productRepository.findAll()).thenReturn(new ArrayList<>());
        var productsFromDB = productServiceImpl.getProducts();
        assertTrue(productsFromDB.isEmpty());
    }

    @Test
    public void testGetProductById() {
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(new Product(1, "Category A")));
        Product product = productServiceImpl.getProductById(1);
        assertNotNull(product);
        assertEquals(1, product.getId());
    }

    @Test
    public void testGetProductsByCategory() {
        List<Product> productsFilteredByCategory = new ArrayList<>();
        Product product1 = new Product(1, "Category A");
        Product product2 = new Product(2, "Category A");
        Product product3 = new Product(3, "Category B");
        productsFilteredByCategory.add(product1);
        productsFilteredByCategory.add(product2);
        Mockito.when(productRepository.findAllByCategory("Category A")).thenReturn(productsFilteredByCategory);
        List<Product> productsFilteredByCategoryResult = productServiceImpl.getProductsByCategory("Category A");
        assertNotNull(productsFilteredByCategoryResult);
        assertEquals(2, productsFilteredByCategoryResult.size());
    }

    @Test
    public void testSaveProduct() {
        Product newProduct = new Product();
        newProduct.setId(3);
        newProduct.setName("Product 3");
        newProduct.setCategory("Category C");

        when(productRepository.save(newProduct)).thenReturn(newProduct);

        Product savedProduct = productServiceImpl.saveProduct(newProduct);
        assertNotNull(savedProduct);
        assertEquals(newProduct, savedProduct);
    }

    @Test
    public void testUpdateProduct() {
        int idToUpdate = 1;
        Product updatedProduct = new Product();
        updatedProduct.setId(1);
        updatedProduct.setName("Updated Product 1");
        updatedProduct.setCategory("Updated Category A");

        when(productRepository.findById(idToUpdate)).thenReturn(Optional.of(updatedProduct));
        when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

        Product result = productServiceImpl.updateProduct(idToUpdate, updatedProduct);
        assertNotNull(result);
        assertEquals("Updated Product 1", result.getName());
        assertEquals("Updated Category A", result.getCategory());

        int idNotPresent= 4;
        when(productRepository.findById(idNotPresent)).thenReturn(Optional.empty());

        try {
            productServiceImpl.updateProduct(idNotPresent, updatedProduct);
            fail("It should thrown exception for an object that doesn't exist");
        } catch (RuntimeException e) {
            assertEquals("Product with id 4 must exist", e.getMessage());
        }
    }

    @Test
    public void testDeleteProduct() {
        int idToDelete = 2;
        productServiceImpl.deleteProduct(idToDelete);

        verify(productRepository, times(1)).deleteById(idToDelete);
    }
}
