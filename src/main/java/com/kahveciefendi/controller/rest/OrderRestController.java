package com.kahveciefendi.controller.rest;

import com.kahveciefendi.dto.DiscountOrderDto;
import com.kahveciefendi.entity.OrderDone;
import com.kahveciefendi.entity.OrderLine;
import com.kahveciefendi.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<DiscountOrderDto> applyDiscount(@RequestBody List<OrderLine> orderList) {
        log.debug("The restful request is apply to discount: {}", orderList);

        return new ResponseEntity<>(orderService.applyDiscountByRules(orderList),HttpStatus.OK);
    }

    @RequestMapping(value = "/orderDone", method = RequestMethod.POST)
    public ResponseEntity<OrderDone> orderDone(@RequestBody DiscountOrderDto discountOrder) {
        log.debug("The restful request is for the final step of the order: {}", discountOrder);

        return new ResponseEntity<>(orderService.orderDone(discountOrder), HttpStatus.OK);
    }

    @RequestMapping(value = "/history/{customerId}", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDone>> orderHistory(@PathVariable(value = "customerId") Long customerId) {
        log.debug("The restful request is lists order history by customer: {}", customerId);

        return new ResponseEntity<>(orderService.orderHistory(customerId), HttpStatus.OK);
    }


}
