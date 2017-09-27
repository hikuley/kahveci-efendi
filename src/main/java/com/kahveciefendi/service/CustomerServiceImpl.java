package com.kahveciefendi.service;

import com.kahveciefendi.dto.LoginDto;
import com.kahveciefendi.entity.Customer;
import com.kahveciefendi.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by hikuley on 22.09.2017.
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HttpServletRequest currentRequest;

    @Override
    public Customer signUp(Customer customer) {
        Customer saved = customerRepository.save(customer);
        return saved;
    }

    @Override
    public Customer login(LoginDto loginDto) {
        Customer customer = customerRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());

        log.debug("Customer authentication started {}", customer);

        HttpSession session = currentRequest.getSession();
        session.setAttribute("customer", customer);

        return customer;
    }

    @Override
    public boolean logout() {

        log.debug("Customer logout.");

        HttpSession session = currentRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        } else
            return false;
        return true;
    }

    @Override
    public boolean hasSession() {

        log.debug("Session available.");

        HttpSession session = currentRequest.getSession(false);
        try {
            Customer user = (Customer) session.getAttribute("customer");
            if (user != null)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Customer getCustomer() {
        HttpSession session = currentRequest.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        return customer;
    }

}
