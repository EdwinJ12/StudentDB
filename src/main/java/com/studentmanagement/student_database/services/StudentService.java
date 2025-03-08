package com.studentmanagement.student_database.services;

import com.studentmanagement.student_database.model.Student;
import org.springframework.stereotype.Service;
import com.studentmanagement.student_database.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public Optional<Student> getStudentById(String id){
        return studentRepository.findById(id);
    }
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    public Student addStudent(Student student){
        return studentRepository.save(student);
    }
    public void deleteStudent(String id){
        studentRepository.deleteById(id);
    }
    public void wipe(){
        studentRepository.deleteAll();
    }
}
