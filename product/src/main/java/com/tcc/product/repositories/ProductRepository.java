package com.tcc.product.repositories;

import com.tcc.product.models.Category;
import com.tcc.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
