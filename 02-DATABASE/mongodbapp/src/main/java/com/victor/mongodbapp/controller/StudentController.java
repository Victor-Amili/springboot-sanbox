//package com.victor.mongodbapp.controller;
//
//import com.victor.h2crud.service.CustomerService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/students")
//public class StudentController {
//
//    private final CustomerService customerService;
//
//    public StudentController(CustomerService customerService)   {
//        this.customerService = customerService;
//    }
//
//    //READ ALL CUSTOMER ENDPOINT
//    //  // GET REQUEST TO RETRIEVE ALL CUSTOMER BY ID
//    @GetMapping
//    public List<com.victor.mongodbapp.entity.Student> getALLCustomer(){
//      return customerService.getAllCustomers();
//    }
//    // GET REQUEST TO RETRIEVE A CUSTOMER BY ID
//    @GetMapping("/{id}")
//    public ResponseEntity<com.victor.mongodbapp.entity.Student> getCustomerById(@PathVariable Integer id) {
//        com.victor.mongodbapp.entity.Student student = customerService.getCustomerById(id);
//        return ResponseEntity.ok(student);
//    }
//
//    // POST CREATES A NEW CUSTOMER
//    @PostMapping
//    public  ResponseEntity<com.victor.h2crud.entity.Student> createCustomer(@RequestBody com.victor.h2crud.entity.Student student){
//        com.victor.h2crud.entity.Student savedStudent = customerService.createCustomer(student);
//        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
//
//    }
//
//    //PUT UPDATES THE CUSTOMER
//    @PutMapping("/{id}")
//    public ResponseEntity<com.victor.h2crud.entity.Student> updateCustomer(@PathVariable Integer id, @RequestBody com.victor.h2crud.entity.Student student){
//
//        com.victor.h2crud.entity.Student updatedStudent = customerService.updateCustomer(id, student);
//        return ResponseEntity.ok(updatedStudent);
//    }
////    @DeleteMapping("/{id}")
////    public ResponseEntity<Void> deleteCustomer(Integer id){
////        customerService.deleteCustomer(id);
////        return  ResponseEntity.noContent().build();
////    }
//
////    @DeleteMapping("/{id}")
////    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
////        if (!customerRepository.existsById(id)) {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with ID " + id + " not found.");
////        }
////
////        customerRepository.deleteById(id);
////        return ResponseEntity.ok("Customer with ID " + id + " deleted successfully.");
////    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
//
//        customerService.deleteCustomer(id);
//        return ResponseEntity.noContent().build();
//    }
//
//
//
//
//}

package com.victor.mongodbapp.controller;

import com.victor.mongodbapp.entity.Student;
import com.victor.mongodbapp.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    // Create Student
    @PostMapping
    public Student saveStudent(@RequestBody Student student){

        return service.saveStudent(student);

    }

    // Get All Students
    @GetMapping
    public List<Student> getStudents(){

        return service.getAllStudents();

    }

    // Get One Student
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id){

        return service.getStudentById(id);

    }

    // Update Student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable String id,
                                 @RequestBody Student student){

        return service.updateStudent(id,student);

    }

    // Delete Student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable String id){

        return service.deleteStudent(id);

    }

}