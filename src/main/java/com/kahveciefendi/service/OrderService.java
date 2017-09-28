package com.kahveciefendi.service;

import com.kahveciefendi.dto.DiscountOrderDto;
import com.kahveciefendi.entity.OrderDone;
import com.kahveciefendi.entity.OrderLine;

import java.util.List;


public interface OrderService {

    DiscountOrderDto applyDiscountByRules(List<OrderLine> orderList);

    OrderDone orderDone(DiscountOrderDto finalOrder);

    List<OrderDone> orderHistory(Long customerId);
}
