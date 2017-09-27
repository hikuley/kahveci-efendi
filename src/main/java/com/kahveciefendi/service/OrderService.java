package com.kahveciefendi.service;

import com.kahveciefendi.dto.DiscountOrderDto;
import com.kahveciefendi.dto.Response;
import com.kahveciefendi.entity.Customer;
import com.kahveciefendi.entity.OrderLine;
import com.kahveciefendi.entity.OrderDone;
import com.kahveciefendi.entity.Product;
import com.kahveciefendi.repository.CustomerRepository;
import com.kahveciefendi.repository.OrderLineRepository;
import com.kahveciefendi.repository.OrderDoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hikuley on 22.09.2017.
 */

@Service
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);


    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderDoneRepository orderDoneRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private CustomerRepository customerRepository;


    public DiscountOrderDto applyDiscountByRules(List<OrderLine> orderList) {

        log.debug("applying discount for total price of the order");

        Double totalPrice = 0.00;
        Double discountPrice = 0.00;
        Double finalPrice;

        DiscountOrderDto discountOrder = new DiscountOrderDto();
        ArrayList<OrderLine> orderLines = new ArrayList<>();

        for (OrderLine orderLine : orderList) {
            Long piece = orderLine.getPiece();
            double productPrice = orderLine.getProduct().getPrice().doubleValue();
            totalPrice += piece * productPrice;

            if (orderLine.getAddons() != null && orderLine.getAddons().size() != 0) {
                for (Product addon : orderLine.getAddons()) {
                    double addonPrice = addon.getPrice().doubleValue();
                    totalPrice += piece * addonPrice;
                }
            }
            orderLines.add(orderLine);
        }

        log.debug("calculated total price:", totalPrice);

        if (totalPrice > 12) {
            discountPrice += totalPrice * 0.25;
        }
        if (orderList.size() >= 3) {
            double minimumPrice = 0;
            for (int i = 0; i < orderList.size(); i++) {
                if (i == 0) {
                    minimumPrice = orderList.get(i).getPrice().doubleValue() * orderList.get(i).getPiece();
                } else {
                    double orderPrice = orderList.get(i).getPrice().doubleValue() * orderList.get(i).getPiece();
                    if (minimumPrice > orderPrice) {
                        minimumPrice = orderPrice;
                    }
                }
            }
            discountPrice += minimumPrice;
        }

        log.debug("calculated total discount price:", discountPrice);

        finalPrice = totalPrice - discountPrice;

        log.debug("calculated final total price:", finalPrice);

        discountOrder.setPrice(totalPrice);
        discountOrder.setDiscountPrice(discountPrice);
        discountOrder.setFinalPrice(finalPrice);
        discountOrder.setOrderLines(orderLines);

        return discountOrder;
    }

    @Transactional
    public Response orderDone(DiscountOrderDto finalOrder) {

        log.debug("The order line items saving to database by customer info.");

        Customer customer = customerRepository.findOne(customerService.getCustomer().getId());
        OrderDone orderDone = new OrderDone();
        orderDone.setCustomer(customer);
        orderDone.setOrderList(finalOrder.getOrderLines());
        orderDone.setFinalPrice(finalOrder.getFinalPrice());
        orderDone.setPrice(finalOrder.getPrice());
        orderDone.setDiscountPrice(finalOrder.getDiscountPrice());

        OrderDone savedOrder = orderDoneRepository.save(orderDone);

        Response response = new Response();
        response.setData(savedOrder);

        return response;
    }
}
