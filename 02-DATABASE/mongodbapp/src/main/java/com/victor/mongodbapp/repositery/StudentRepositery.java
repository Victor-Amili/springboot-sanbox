package com.victor.mongodbapp.repositery;

import com.victor.mongodbapp.entity.Student;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepositery extends MongoRepository<Student,String> {
}
