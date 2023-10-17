package com.ing.storemanagement.service.impl;

import com.ing.storemanagement.domain.Product;
import com.ing.storemanagement.repository.ProductRepository;
import com.ing.storemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl() {}

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        Assert.notNull(id, "Id of product must not be null");
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()) {
            throw new RuntimeException("Din not find product with id" + id);
        }
        return product.get();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        Assert.notNull(category, "Category of product must not be null");
        List<Product> products = productRepository.findAllByCategory(category);
        if(products.isEmpty()) {
            throw new RuntimeException("Din not find product with category" + category);
        }
        return products;
    }

    @Override
    public Product saveProduct(Product product) {
        Assert.notNull(product, "Product must not be null.");
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(int id_product, Product product) {
        Assert.notNull(product, "Product must not be null.");
        Assert.notNull(id_product, "Product must specify an ID");
        boolean existing = productRepository.findById(id_product).isPresent();
        if (!existing) {
            throw new RuntimeException("Product with id " + id_product + " must exist");
        }
        Product productToUpdate = productRepository.findById(id_product).get();
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setPrice(product.getPrice());
        return productRepository.save(productToUpdate);
    }

    @Override
    public void deleteProduct(int id) {
        Assert.notNull(id, "Id of product must not be null");
        productRepository.deleteById(id);
    }
}
