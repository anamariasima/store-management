package com.ing.storemanagement.controller;

import com.ing.storemanagement.domain.Product;
import com.ing.storemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/productsFilteredByCategory")
    List<Product> getProductsByCategory(@RequestParam(name = "category") String category) {
        return productService.getProductsByCategory(category);
    }

    @PostMapping("/products")
    Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/products/{id}")
    Product updateProduct(@RequestBody Product product, @PathVariable int id) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    void deleteProductById(@PathVariable int id) {
        productService.deleteProduct(id);
    }
}
