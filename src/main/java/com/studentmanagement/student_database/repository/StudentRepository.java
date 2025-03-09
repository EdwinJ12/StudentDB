package com.studentmanagement.student_database.repository;
import com.studentmanagement.student_database.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student,String> {
    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    List<Student> findByName(String name);
    List<Student> findByAge(int age);
    @Query("{ 'course': { $regex: ?0, $options: 'i' } }")
    List<Student> findByCourse(String course);
    // place to write custom query methods..//check this part later
}