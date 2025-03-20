package com.studentmanagement.student_database.repository;

import com.studentmanagement.student_database.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByUsername(String username);
    //custom queries
}
