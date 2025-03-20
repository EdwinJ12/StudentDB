package com.studentmanagement.student_database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private Set<String> roles;

    public User() {}
    public User(String id , String username , String password, Set<String> roles){
        this.id=id;
        this.username=username;
        this.password=password;
        this.roles=roles;
    }

    public String getId() {
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public Set<String> getRoles(){
        return roles;
    }
    public void setUsername(String username){
        this.username=username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setId(String id) {
        this.id = id;
    }
}
