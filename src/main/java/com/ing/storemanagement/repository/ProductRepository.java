package com.ing.storemanagement.repository;

import com.ing.storemanagement.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Integer id);

    @Query("select p from Product p where p.category = :category")
    List<Product>  findAllByCategory(String category);

    @Override
    Product save(Product product);

    @Override
    void deleteById(Integer id);
}
