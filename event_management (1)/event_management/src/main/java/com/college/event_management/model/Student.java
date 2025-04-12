package com.college.event_management.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    private String id;
    private String name;
    private String department;
    private String mobile;
    private String email;
    private String password;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Registration> registrations;
    
    // Constructors
    public Student() {}
    
    public Student(String id, String name, String department, String mobile, String email, String password) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public List<Registration> getRegistrations() { return registrations; }
    public void setRegistrations(List<Registration> registrations) { 
        this.registrations = registrations; 
    }
}