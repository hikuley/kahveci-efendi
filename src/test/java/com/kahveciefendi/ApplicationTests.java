package com.kahveciefendi;

import com.kahveciefendi.entity.Product;
import com.kahveciefendi.entity.ProductType;
import com.kahveciefendi.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {


    @Autowired
    ProductRepository productRepository;




    @Test
    public void contextLoads() {

        Product product = new Product();
        product.setName("test");
        product.setDescription("testtest");
        product.setProductType(ProductType.ADDONE);
        product.setPrice(10.5);

        productRepository.save(product);

    }

}
