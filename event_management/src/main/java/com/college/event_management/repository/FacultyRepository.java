package com.college.event_management.repository;

import com.college.event_management.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, String> {
    Faculty findByEmail(String email);
    Faculty findByEmailAndPassword(String email, String password);
}