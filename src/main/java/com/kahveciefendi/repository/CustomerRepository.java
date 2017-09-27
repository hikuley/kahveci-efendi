package com.kahveciefendi.repository;

import com.kahveciefendi.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by hikuley on 22.09.2017.
 */

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
