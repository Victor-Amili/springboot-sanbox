package com.victor.h2crud.controller;

import com.victor.h2crud.entity.Customer;
import com.victor.h2crud.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService)   {
        this.customerService = customerService;
    }

    //READ ALL CUSTOMER ENDPOINT
    //  // GET REQUEST TO RETRIEVE ALL CUSTOMER BY ID
    @GetMapping
    public List<Customer> getALLCustomer(){
      return customerService.getAllCustomers();
    }
    // GET REQUEST TO RETRIEVE A CUSTOMER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    // POST CREATES A NEW CUSTOMER
    @PostMapping
    public  ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);

    }

    //PUT UPDATES THE CUSTOMER
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer){

        Customer updatedCustomer= customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(updatedCustomer);
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCustomer(Integer id){
//        customerService.deleteCustomer(id);
//        return  ResponseEntity.noContent().build();
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
//        if (!customerRepository.existsById(id)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with ID " + id + " not found.");
//        }
//
//        customerRepository.deleteById(id);
//        return ResponseEntity.ok("Customer with ID " + id + " deleted successfully.");
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {

        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }




}
