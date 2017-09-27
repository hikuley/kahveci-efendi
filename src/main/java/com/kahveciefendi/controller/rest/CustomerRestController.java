package com.kahveciefendi.controller.rest;

import com.kahveciefendi.dto.CustomerDto;
import com.kahveciefendi.dto.LoginDto;
import com.kahveciefendi.dto.ResponseDto;
import com.kahveciefendi.entity.Customer;
import com.kahveciefendi.service.CustomerServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class CustomerRestController {

    private final Logger log = LoggerFactory.getLogger(CustomerRestController.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerServiceImpl customerService;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> signUp(@RequestBody @Valid CustomerDto customerDto, BindingResult bindingResult) {

        log.debug("The restful request for new user: {}", customerDto);

        ResponseDto responseDto = new ResponseDto();
        if (!bindingResult.hasErrors()) {
            Customer customer = modelMapper.map(customerDto, Customer.class);
            Customer savedUser = customerService.signUp(customer);
            responseDto.setStatus(savedUser != null);
            responseDto.setData(savedUser);
            responseDto.setMessage("Kayıt başarılı.");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);

        } else {
            responseDto.setFieldErrors(bindingResult.getFieldErrors());
            responseDto.setMessage("Kayıt başarısız");
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> login(@RequestBody @Valid LoginDto loginDto, BindingResult bindingResult) {

        log.debug("REST request for login customer : {}", loginDto);

        ResponseDto responseDto = new ResponseDto();
        if (!bindingResult.hasErrors()) {
            Customer customer = customerService.login(loginDto);
            if (customer != null) {
                responseDto.setData(customer);
                responseDto.setMessage("Giriş başarılı.");
                responseDto.setStatus(customer != null);
            } else {
                responseDto.setMessage("Giriş başarısız.");
                responseDto.setStatus(customer == null);
            }
        } else {
            responseDto.setFieldErrors(bindingResult.getFieldErrors());

        }
        return ResponseEntity.ok(responseDto);
    }

}
