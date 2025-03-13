package com.studentmanagement.student_database.services;

import com.studentmanagement.student_database.model.Student;
import org.springframework.stereotype.Service;
import com.studentmanagement.student_database.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
    public String addStudent(Student student){
        if(student.getId()==null||student.getId().isEmpty()){
            student.setId(generateRandomId(8));
        }
        Optional<Student>existingStudent = studentRepository.findById(student.getId());
        if(existingStudent.isPresent()){
            return "Id already exists. Please use update ID or delete and configure a new one.";
        }else {
            studentRepository.save(student);
            return "Student added successfully";
        }
    }
    public void deleteStudent(String id){
        studentRepository.deleteById(id);
    }
    public void wipe(){
        studentRepository.deleteAll();
    }
    public long getCount(){
        return studentRepository.count();
    }
    public String updateStudent(Student student){
        Optional<Student>existing= studentRepository.findById(student.getId());
        if(existing.isPresent()){
            addStudent(student);
            return "Student updated Successfully";
        }
        return "Non Existing Student please add student";
    }
    public List<Student> search(String name, Integer age,String course){
        if(name!=null){
            return studentRepository.findByName(name);
        }else if (age!=null){
            return studentRepository.findByAge(age);
        }else if(course!=null){
            return studentRepository.findByCourse(course);
        }
        return studentRepository.findAll();
    }
    public String addRandomStudent(){
        Random random = new Random();
        String id = generateRandomId(8);
        int randomAge = 18 + random.nextInt(10);
        String randomName = generateRandomName();
        String randomCourse = generateRandomCourse();
        Student randomStudent =new Student(randomName,id, randomCourse,randomAge);
        studentRepository.save(randomStudent);
        return "Student added";
    }
    public String generateRandomId(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("ID length must be greater than 0");
        }
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Allowed characters
        Random random = new Random();
        StringBuilder id = new StringBuilder(length); // Use the length parameter here instead of hardcoded 4
        for (int i = 0; i < length; i++) {
            id.append(characters.charAt(random.nextInt(characters.length())));
        }
        return id.toString();
    }
    private String generateRandomName(){
        Random random = new Random();
        List<String> FirstName= List.of("John", "Alice", "Michael", "Sarah", "David", "Emma", "Robert", "Sophia", "James", "Olivia");
        List<String> LastName = List.of("Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", "Martinez", "Wilson");

        String firstName = FirstName.get(random.nextInt(FirstName.size()));
        String lastName = LastName.get(random.nextInt(LastName.size()));

        return firstName + " " + lastName;
    }
    private String generateRandomCourse(){
        Random random = new Random();
        List<String> courses= List.of(
                "Computer Science",
                "Mechanical Engineering",
                "Electrical Engineering",
                "Civil Engineering",
                "Aerospace Engineering",
                "Biomedical Engineering",
                "Chemical Engineering",
                "Environmental Science",
                "Data Science",
                "Artificial Intelligence",
                "Cyber Security",
                "Information Technology",
                "Software Engineering",
                "Business Administration",
                "Human Resource",
                "Financial Management",
                "Marketing Management",
                "Agricultural Science",
                "Nuclear Physics",
                "Mathematical Computing");
        String course = courses.get(random.nextInt(courses.size()));

        return course;
    }
    public Student generateRandomStudent(){
        Random random = new Random();
        String id = generateRandomId(8);
        int randomAge = 18 + random.nextInt(10);
        String randomName = generateRandomName();
        String randomCourse = generateRandomCourse();
        Student randomStudent =new Student(randomName,id, randomCourse,randomAge);
        return randomStudent;
    }

}
