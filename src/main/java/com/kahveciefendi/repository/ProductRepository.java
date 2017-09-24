package com.kahveciefendi.repository;

import com.kahveciefendi.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
