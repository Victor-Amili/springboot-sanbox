package com.victor.mongodbapp.service;

import com.victor.mongodbapp.entity.Student;
import com.victor.mongodbapp.repositery.StudentRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepositery repositery;

    // Create Student
    public Student saveStudent(Student student) {
        return repositery.save(student);
    }

    // Get All Students
    public List<Student> getAllStudents() {
        return repositery.findAll();
    }

    // Get One Student
    public Student getStudentById(String id) {

        Optional<Student> student = repositery.findById(id);

        if(student.isPresent()) {
            return student.get();
        }

        return null;
    }

    // Update Student
    public Student updateStudent(String id, Student newStudent) {

        Optional<Student> optionalStudent = repositery.findById(id);

        if(optionalStudent.isPresent()) {

            Student student = optionalStudent.get();

            student.setFirstName(newStudent.getFirstName());
            student.setLastName(newStudent.getLastName());
            student.setAge(newStudent.getAge());

            return repositery.save(student);
        }

        return null;
    }

    // Delete Student
    public String deleteStudent(String id) {

        repositery.deleteById(id);

        return "Student Deleted Successfully";

    }

}