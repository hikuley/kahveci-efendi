package com.kahveciefendi.service;

import com.kahveciefendi.dto.DiscountOrderDto;
import com.kahveciefendi.entity.Customer;
import com.kahveciefendi.entity.OrderDone;
import com.kahveciefendi.entity.OrderLine;
import com.kahveciefendi.entity.Product;
import com.kahveciefendi.repository.CustomerRepository;
import com.kahveciefendi.repository.OrderDoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hikuley on 22.09.2017.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    public static final double RULE_PERCENTAGE_DISCOUNT = 0.25;
    public static final double RULE_TOTAL_BASKET_DISCOUNT = 12;
    public static final double RULE_PIECE_DISCOUNT = 3;


    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderDoneRepository orderDoneRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public DiscountOrderDto applyDiscountByRules(List<OrderLine> orderList) {

        log.debug("applying discount for total price of the order");

        Double totalPrice = 0.00;
        Double totalProductDiscountPrice = 0.00;
        Double totalAddonDiscountPrice = 0.00;
        Double finalPrice;
        int totalPiece = 0;

        DiscountOrderDto discountOrder = new DiscountOrderDto();
        ArrayList<OrderLine> orderLines = new ArrayList<>();

        for (OrderLine orderLine : orderList) {
            Long piece = orderLine.getPiece();
            double productPrice = orderLine.getProduct().getPrice().doubleValue();
            totalPrice += piece * productPrice;
            totalPiece += piece;
            if (orderLine.getAddons() != null && orderLine.getAddons().size() != 0) {
                for (Product addon : orderLine.getAddons()) {
                    double addonPrice = addon.getPrice().doubleValue();
                    totalPrice += piece * addonPrice;
                }
            }
            orderLines.add(orderLine);
        }

        log.debug("calculated total price:", totalPrice);

        if (totalPrice > OrderServiceImpl.RULE_TOTAL_BASKET_DISCOUNT) {
            totalProductDiscountPrice += totalPrice * OrderServiceImpl.RULE_PERCENTAGE_DISCOUNT;
        }
        if (totalPiece >= OrderServiceImpl.RULE_PIECE_DISCOUNT) {
            double minimumPrice = 0;
            int minimumIndex = 0;
            for (int i = 0; i < orderList.size(); i++) {
                Long piece = orderList.get(i).getPiece();
                if (i == 0) {
                    minimumPrice = orderList.get(i).getPrice().doubleValue() / piece;
                } else {
                    double orderPrice = orderList.get(i).getPrice().doubleValue();

                    if (minimumPrice > orderPrice) {
                        minimumPrice = orderPrice / piece;
                        minimumIndex = i;
                    }
                }
            }

            for (Product product : orderList.get(minimumIndex).getAddons()) {
                totalAddonDiscountPrice += product.getPrice().doubleValue();
            }

            totalProductDiscountPrice += minimumPrice;
            totalProductDiscountPrice += totalAddonDiscountPrice;
        }

        log.debug("calculated total discount price:", totalProductDiscountPrice);

        finalPrice = totalPrice - totalProductDiscountPrice;

        log.debug("calculated final total price:", finalPrice);

        discountOrder.setPrice(totalPrice);
        discountOrder.setDiscountPrice(totalProductDiscountPrice);
        discountOrder.setFinalPrice(finalPrice);
        discountOrder.setOrderLines(orderLines);

        return discountOrder;
    }

    @Transactional
    @Override
    public OrderDone orderDone(DiscountOrderDto finalOrder) {

        log.debug("The order line items saving to database by customer info.");

        Customer customer = customerRepository.findOne(customerService.getCustomer().getId());
        OrderDone orderDone = new OrderDone();
        orderDone.setCustomer(customer);
        orderDone.setOrderList(finalOrder.getOrderLines());
        orderDone.setFinalPrice(finalOrder.getFinalPrice());
        orderDone.setPrice(finalOrder.getPrice());
        orderDone.setDiscountPrice(finalOrder.getDiscountPrice());

        return orderDoneRepository.save(orderDone);
    }

    @Override
    public List<OrderDone> orderHistory(Long customerId) {
        List<OrderDone> orders = orderDoneRepository.findOrderDonesByCustomerId(customerId);
        return orders;
    }
}
