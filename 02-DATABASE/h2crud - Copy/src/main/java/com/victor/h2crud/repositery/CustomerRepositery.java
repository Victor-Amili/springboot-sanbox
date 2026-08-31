package com.victor.h2crud.repositery;

import com.victor.h2crud.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositery extends JpaRepository<Customer, Integer> {

}
