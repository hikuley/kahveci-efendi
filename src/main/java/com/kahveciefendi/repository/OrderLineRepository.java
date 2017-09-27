package com.kahveciefendi.repository;

import com.kahveciefendi.entity.OrderLine;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hikuley on 22.09.2017.
 */

public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {

}
