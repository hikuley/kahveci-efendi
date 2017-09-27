package com.kahveciefendi.repository;

import com.kahveciefendi.entity.OrderDone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hikuley on 22.09.2017.
 */

public interface OrderDoneRepository extends CrudRepository<OrderDone, Long> {
    List<OrderDone> findOrderDonesByCustomerId(Long customerId);
}
