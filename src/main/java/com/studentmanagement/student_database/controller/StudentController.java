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
        this.studentServices = studentServices;
    }

    @GetMapping("/list")
    public List<Student> getAllStudents(){
        return studentServices.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id){
        return studentServices.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        String response = studentServices.addStudent(student);
        if (response.startsWith("Id already exists")){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok().body(response);
    }
    @PatchMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody Student student){
        String response = studentServices.updateStudent(student);
        if(response.startsWith("Student updated Successfully")){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @DeleteMapping("/deleteStudentById/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable String id){
        studentServices.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/wipe")
    public void wipe(){
        studentServices.wipe();
    }

    @GetMapping("/test")
    public String test() {
        return studentServices.generateRandomId(4);
    }

    @GetMapping("/getCount")
    public  long getCount(){
        return  studentServices.getCount();
    }
    @GetMapping("/search")
    public ResponseEntity<Object> searchStudents(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) Integer age,
                                                  @RequestParam(required = false) String course){
        List<Student> students = studentServices.search(name,age,course);
        if (students.isEmpty()) {
            return ResponseEntity.badRequest().body("No results found matching search Criteriaa");
        }
        return ResponseEntity.ok().body(students);
    }
    @PostMapping("/addRandomStudent")
    public ResponseEntity<String> addRandomStudent(){
        String response = studentServices.addRandomStudent();
        if (response.startsWith("Student added")){
            return ResponseEntity.ok().body("Random Student added");
        }
        return ResponseEntity.badRequest().body("Random Student Creation failed");
    }


}