package com.kahveciefendi.controller.rest;

import com.kahveciefendi.dto.CustomerDto;
import com.kahveciefendi.dto.LoginDto;
import com.kahveciefendi.dto.Response;
import com.kahveciefendi.entity.Customer;
import com.kahveciefendi.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by hikuley on 22.09.2017.
 */

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerRestController  {

    private final Logger log = LoggerFactory.getLogger(CustomerRestController.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public Response signUp(@RequestBody @Valid CustomerDto customerDto, BindingResult bindingResult) {

        log.debug("REST request for new user: {}", customerDto);

        Response response = new Response();
        if (!bindingResult.hasErrors()) {
            Customer customer = modelMapper.map(customerDto, Customer.class);
            Customer savedUser = customerService.signUp(customer);
            response.setData(savedUser);
            response.setStatus(HttpStatus.OK);
            response.setMessage("Kayıt başarılı.");
            return response;
        } else {
            response.setFieldErrors(bindingResult.getFieldErrors());
            response.setStatus(HttpStatus.BAD_REQUEST);
            return response;
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody @Valid LoginDto loginDto, BindingResult bindingResult) {

        log.debug("REST request for login customer : {}", loginDto);

        Response response = new Response();
        if (!bindingResult.hasErrors()) {
            Customer customer = customerService.login(loginDto);
            if (customer != null) {
                response.setStatus(HttpStatus.OK);
                response.setData(customer);
                response.setMessage("Giriş başarılı.");
            } else {
                response.setStatus(HttpStatus.FOUND);
                response.setMessage("Giriş başarısız.");
            }
        } else {
            response.setFieldErrors(bindingResult.getFieldErrors());
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        return response;
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Response logout() {

        log.debug("REST request for customer logout");

        Response response = new Response();
        if (customerService.logout()) {
            response.setStatus(HttpStatus.OK);
            response.setMessage("Çıkış başarılı");
        } else {
            response.setStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
            response.setMessage("Çıkış başarısız");
        }
        return response;
    }


}
