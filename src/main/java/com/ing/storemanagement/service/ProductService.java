package com.ing.storemanagement.service;

import com.ing.storemanagement.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ProductService {

    List<Product> getProducts();

    Product getProductById(int id);

    List<Product> getProductsByCategory(String category);

    default List<Product> getProductsInPriceRange(int min, int max) {
        List<Product> products = getProducts();
        List<Product> productsFilteredByPrice = filterProductsByPrice(products, min, max);
        return  productsFilteredByPrice;
    }

    private static List<Product> filterProductsByPrice(List<Product> products, int min, int max) {
        return products.stream().filter(e -> e.getPrice() > min && e.getPrice() < max).collect(Collectors.toList());
    }

    Product saveProduct(Product product);

    Product updateProduct(int id_product, Product product);

    Product changePrice(int id_product, double price);

    void deleteProduct(int id);

}
