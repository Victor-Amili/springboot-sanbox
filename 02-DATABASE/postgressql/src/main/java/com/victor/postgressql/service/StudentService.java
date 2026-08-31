package com.victor.postgressql.service;

import com.victor.postgressql.entity.Student;
import com.victor.postgressql.repositery.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // CREATE
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // READ ALL
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // READ ONE
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElse(null);
    }

    // UPDATE
    public Student updateStudent(Long id, Student newStudent) {

        Student existingStudent = studentRepository
                .findById(id)
                .orElse(null);

        if (existingStudent == null) {
            return null;
        }

        existingStudent.setFirstName(newStudent.getFirstName());
        existingStudent.setLastName(newStudent.getLastName());
        existingStudent.setAge(newStudent.getAge());

        return studentRepository.save(existingStudent);
    }

    // DELETE
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}