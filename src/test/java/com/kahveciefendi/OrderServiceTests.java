package com.kahveciefendi;

import com.kahveciefendi.service.OrderService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {


    @Autowired
    OrderService customerService;

    @BeforeClass
    public static void userServiceInitial() {


    }

    @Before
    public void userServiceBefore() {

    }


}
