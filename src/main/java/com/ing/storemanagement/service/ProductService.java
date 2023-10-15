package com.ing.storemanagement.service;

import com.ing.storemanagement.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getProducts();

    Product getProductById(int id);

    List<Product> getProductsByCategory(String category);

    Product saveProduct(Product product);

    Product updateProduct(int id_product, Product product);

    void deleteProduct(int id);

}
