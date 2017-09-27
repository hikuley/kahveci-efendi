package com.kahveciefendi.controller.rest;

import com.kahveciefendi.entity.Product;
import com.kahveciefendi.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
public class ProductRestController {

    private final Logger log = LoggerFactory.getLogger(ProductRestController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Product> products() {
        return productService.allList();
    }

}
