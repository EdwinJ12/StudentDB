package com.studentmanagement.student_database.controller;

import com.studentmanagement.student_database.model.User;
import com.studentmanagement.student_database.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }
    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return "User already Exist";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton("ROLE_USER"));
        userRepository.save(user);
        return "User Saved";
    }
}
