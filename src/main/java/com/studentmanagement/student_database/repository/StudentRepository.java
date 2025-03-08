package com.studentmanagement.student_database.repository;
import com.studentmanagement.student_database.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,String> {
    // place to write custom query methods..//check this part later
}