package com.studentmanagement.student_database.controller;

import com.studentmanagement.student_database.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.studentmanagement.student_database.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentServices;

    public StudentController(StudentService studentServices){
        this.studentServices=studentServices;
    }

    @GetMapping("/list")
    public List<Student> getAllStudents(){
        return studentServices.getAllStudents();
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<Student>getStudentById(@PathVariable String id){
        return studentServices.getStudentById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student){
        return studentServices.addStudent(student);
    }
    @DeleteMapping("deleteStudentById/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable String id){
        studentServices.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/wipe")
    public void wipe(){
        studentServices.wipe();
    }
    //@RestController
    public class TestController {
        @GetMapping("/test")
        public String test() {
            return "Hello, World!";
        }
    }

}
