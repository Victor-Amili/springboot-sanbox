package com.victor.postgressql.repositery;

import com.victor.postgressql.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}