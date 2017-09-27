package com.kahveciefendi.service;

import com.kahveciefendi.dto.LoginDto;
import com.kahveciefendi.entity.Customer;

public interface CustomerService {

    Customer signUp(Customer customer);

    Customer login(LoginDto loginDto);

    boolean logout();

    boolean hasSession();

    Customer getCustomer();

}
