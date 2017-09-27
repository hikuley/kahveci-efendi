package com.kahveciefendi.controller.rest;

import com.kahveciefendi.dto.DiscountOrderDto;
import com.kahveciefendi.dto.Response;
import com.kahveciefendi.entity.OrderLine;
import com.kahveciefendi.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hikuley on 22.09.2017.
 */

@RestController
@RequestMapping(value = "/api/order")
public class OrderRestController {

    private final Logger log = LoggerFactory.getLogger(OrderRestController.class);

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/applyDiscount", method = RequestMethod.POST)
    public DiscountOrderDto applyDiscount(@RequestBody List<OrderLine> orderList) {
        return orderService.applyDiscountByRules(orderList);
    }

    @RequestMapping(value = "/orderDone", method = RequestMethod.POST)
    public Response orderDone(@RequestBody DiscountOrderDto discountOrder) {
        return orderService.orderDone(discountOrder);
    }

}
