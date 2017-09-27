package com.kahveciefendi;

import com.kahveciefendi.entity.Product;
import com.kahveciefendi.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTests {


    @Autowired
    ProductService productService;

    @Test
    public void testAllProductList() {
        List<Product> productList = productService.allProductList();
        Assert.assertNotNull("Could not record", productList);
    }


}
