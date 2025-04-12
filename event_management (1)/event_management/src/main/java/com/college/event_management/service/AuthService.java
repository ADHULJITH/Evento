package com.college.event_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.event_management.model.Faculty;
import com.college.event_management.model.Student;
import com.college.event_management.repository.FacultyRepository;
import com.college.event_management.repository.StudentRepository;

@Service
public class AuthService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Faculty Authentication
    public boolean registerFaculty(Faculty faculty) {
        if (facultyRepository.findByEmail(faculty.getEmail()) != null) {
            return false;
        }
        facultyRepository.save(faculty);
        return true;
    }

    public Faculty authenticateFaculty(String email, String password) {
        return facultyRepository.findByEmailAndPassword(email, password);
    }

    // Student Authentication
    public boolean registerStudent(Student student) {
        if (studentRepository.findByEmail(student.getEmail()) != null) {
            return false;
        }
        studentRepository.save(student);
        return true;
    }

    public Student authenticateStudent(String email, String password) {
        return studentRepository.findByEmailAndPassword(email, password);
    }
}