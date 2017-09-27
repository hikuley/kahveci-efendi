package com.kahveciefendi;

import com.kahveciefendi.dto.LoginDto;
import com.kahveciefendi.entity.Customer;
import com.kahveciefendi.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTests {


    @Autowired
    CustomerService customerService;


    Customer customer;
    static Long testId;


    @BeforeClass
    public static void userServiceInitial() {
        // Before Test initializing

    }

    @Before
    public void userServiceBefore() {
        customer = new Customer();
        customer.setName("Halil İbrahim");
        customer.setSurname("Küley");
        customer.setUsername("hikuley");
        customer.setPassword("1234225");
    }

    @Test
    public void test1_signUp() {
        Customer savedCustomer = customerService.signUp(customer);
        Assert.assertNotNull("Could not record", savedCustomer);
        testId = savedCustomer.getId();
    }

    @Test
    public void test2_login() {

        LoginDto loginDto = new LoginDto();

        loginDto.setUsername("test");
        loginDto.setPassword("test");

        Customer loginResponse = customerService.login(loginDto);
        Assert.assertNotNull("login failed", loginResponse);
    }

    @Test
    public void test3_logout() {
        boolean logout = customerService.logout();

        Assert.assertFalse("logout failed", logout);
    }


    @Test
    public void test4_hasSession() {
        boolean hasSession = customerService.hasSession();

        Assert.assertFalse("the session not available", hasSession);
    }


    @Test
    public void test4_getCustomer() {
        Customer customer = customerService.getCustomer();

        Assert.assertNotNull("Could not record", customer);
    }
}
