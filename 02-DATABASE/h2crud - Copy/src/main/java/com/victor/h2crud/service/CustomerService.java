package com.victor.h2crud.service;

import com.victor.h2crud.entity.Customer;
import com.victor.h2crud.repositery.CustomerRepositery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepositery customerRepositery;

    public CustomerService(CustomerRepositery customerRepositery) {
        this.customerRepositery = customerRepositery;
    }

    public List<Customer> getAllCustomers(){
        return customerRepositery.findAll();
    }

    public Customer createCustomer (Customer customer){
        return  customerRepositery.save(customer);
    }

    public Customer getCustomerById(Integer id){
        return customerRepositery.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
    }


   public Customer updateCustomer(Integer id, Customer updatedCustomer){
        Customer existingCustomer = getCustomerById(id);

       existingCustomer.setName(updatedCustomer.getName());
       existingCustomer.setAddress(updatedCustomer.getAddress());
       existingCustomer.setGender(updatedCustomer.getGender());

       return customerRepositery.save(existingCustomer);
   }


   public void deleteCustomer(Integer id){
        Customer existingCustomer = getCustomerById(id);

        customerRepositery.delete(existingCustomer);
   }


}


