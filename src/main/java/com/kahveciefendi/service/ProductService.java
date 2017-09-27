package com.kahveciefendi.service;

import com.kahveciefendi.entity.Product;
import com.kahveciefendi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> allList() {
        Iterable<Product> iterable = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        iterable.forEach(productList::add);
        return productList;
    }
}
