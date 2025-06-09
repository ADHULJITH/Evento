package com.college.event_management.repository;

import com.college.event_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByEmail(String email);
    Student findByEmailAndPassword(String email, String password);
}