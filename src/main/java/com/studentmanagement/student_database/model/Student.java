package com.studentmanagement.student_database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
public class Student {        //student class
    @Id
    private String id;
    private String name;
    private int age;
    private String course;

    public Student() {}
    public Student(String name, String course, int age) {
        this.name=name;
        this.course=course;
        this.age=age;
    }

   public String getId(){
        return id;
   }
   public void setId(String id){
        this.id=id;
   }

   public String getName(){
        return name;
   }
   public void setName(String name){
        this.name=name;
   }

   public  String getCourse(){
        return course;
   }
   public void setCourse(String course){
        this.course=course;
   }

   public  int getAge(){
        return age;
   }
   public void setAge(int age){
        this.age=age;
   }

}
