package com.kahveciefendi.controller.rest;

import com.kahveciefendi.entity.Product;
import com.kahveciefendi.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
public class ProductRestController {

    private final Logger log = LoggerFactory.getLogger(ProductRestController.class);

    @Autowired
    private ProductServiceImpl productService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> products() {
        List<Product> productList = productService.allProductList();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
