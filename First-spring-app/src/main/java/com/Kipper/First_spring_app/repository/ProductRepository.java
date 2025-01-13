package com.Kipper.First_spring_app.repository;

import com.Kipper.First_spring_app.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
