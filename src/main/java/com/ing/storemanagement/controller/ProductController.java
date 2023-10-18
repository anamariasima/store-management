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

    @GetMapping("/productsFilteredByPrice")
    List<Product> getProductsInPriceRange(@RequestParam(name = "min") int min, @RequestParam(name = "max") int max) {
        return productService.getProductsInPriceRange(min, max);
    }

    @PostMapping("/products")
    Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/products/{id}")
    Product updateProduct(@RequestBody Product product, @PathVariable int id) {
        return productService.updateProduct(id, product);
    }

    @PutMapping("/products/update-price/{id}")
    Product changePrice(@PathVariable int id, @RequestParam(name = "price") double price) {
        return productService.changePrice(id, price);
    }

    @DeleteMapping("/products/{id}")
    void deleteProductById(@PathVariable int id) {
        productService.deleteProduct(id);
    }
}
