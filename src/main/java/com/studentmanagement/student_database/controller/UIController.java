package com.studentmanagement.student_database.controller;

import com.studentmanagement.student_database.model.Student;
import com.studentmanagement.student_database.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ui")
public class UIController {
    private final StudentService studentService;

    public UIController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        model.addAttribute("student", new Student()); // For the add student form
        model.addAttribute("count", studentService.getCount());
        return "students";
    }

    @GetMapping("/students/{id}")
    public String getStudentById(@PathVariable String id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "student-details";
        } else {
            return "redirect:/ui/students?error=Student+not+found";
        }
    }

    @PostMapping("/students/add")
    public String addStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        String response = studentService.addStudent(student);
        if (response.startsWith("Id already exists")) {
            redirectAttributes.addFlashAttribute("error", response);
        } else {
            redirectAttributes.addFlashAttribute("success", response);
        }
        return "redirect:/ui/students";
    }

    @PostMapping("/students/update")
    public String updateStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        String response = studentService.updateStudent(student);
        if (response.startsWith("Student updated Successfully")) {
            redirectAttributes.addFlashAttribute("success", response);
        } else {
            redirectAttributes.addFlashAttribute("error", response);
        }
        return "redirect:/ui/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable String id, RedirectAttributes redirectAttributes) {
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("success", "Student deleted successfully");
        return "redirect:/ui/students";
    }

    @GetMapping("/students/wipe")
    public String wipeAllStudents(RedirectAttributes redirectAttributes) {
        studentService.wipe();
        redirectAttributes.addFlashAttribute("success", "All students have been deleted");
        return "redirect:/ui/students";
    }

    @GetMapping("/students/random")
    public String addRandomStudent(RedirectAttributes redirectAttributes) {
        Student randomStudent = studentService.generateRandomStudent(); // Make sure this method exists
        String response = studentService.addStudent(randomStudent);
        if (response.startsWith("Student added")) {
            redirectAttributes.addFlashAttribute("success", "Random student added successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to add random student");
        }
        return "redirect:/ui/students";
    }

    @GetMapping("/students/search")
    public String searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String course,
            Model model) {

        List<Student> students = studentService.search(name, age, course);
        model.addAttribute("students", students);
        model.addAttribute("searchParams", new SearchParams(name, age, course));
        model.addAttribute("count", students.size());
        return "search-results";
    }

    // Helper class for search parameters
    public static class SearchParams {
        private String name;
        private Integer age;
        private String course;

        public SearchParams(String name, Integer age, String course) {
            this.name = name;
            this.age = age;
            this.course = course;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }
    }
}