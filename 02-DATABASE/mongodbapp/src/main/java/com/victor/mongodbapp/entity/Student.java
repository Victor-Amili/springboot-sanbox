package com.victor.mongodbapp.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//
//@Entity
//public class Student {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//   private int id;
//
//   private String name;
//
//    private String address;
//
//    private String gender;
//
//    public Student(int id, String name, String address, String gender) {
//        this.id = id;
//        this.name = name;
//        this.address = address;
//        this.gender = gender;
//    }
//
//    public Student() {
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//}



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "students")

public class Student {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private Integer age;

}