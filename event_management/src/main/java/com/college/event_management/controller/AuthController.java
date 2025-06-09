package com.college.event_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.college.event_management.dto.FacultyRegistrationDto;
import com.college.event_management.dto.StudentRegistrationDto;
import com.college.event_management.model.Faculty;
import com.college.event_management.model.Student;
import com.college.event_management.service.AuthService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    // Faculty Registration
    @GetMapping("/faculty-register")
    public String showFacultyRegisterForm(Model model) {
        model.addAttribute("faculty", new FacultyRegistrationDto());
        return "auth/faculty-register";
    }

    @PostMapping("/faculty-register")
    public String registerFaculty(@ModelAttribute FacultyRegistrationDto facultyDto, 
                                HttpSession session) {
        Faculty faculty = new Faculty();
        faculty.setId(facultyDto.getId());
        faculty.setName(facultyDto.getName());
        faculty.setDepartment(facultyDto.getDepartment());
        faculty.setMobile(facultyDto.getMobile());
        faculty.setEmail(facultyDto.getEmail());
        faculty.setPassword(facultyDto.getPassword());

        if(authService.registerFaculty(faculty)) {
            session.setAttribute("facultyId", faculty.getId());
            return "redirect:/faculty/dashboard";
        }
        return "redirect:/auth/faculty-register?error";
    }

    // Faculty Login
    @GetMapping("/faculty-login")
    public String showFacultyLoginForm() {
        return "auth/faculty-login";
    }

    @PostMapping("/faculty-login")
    public String loginFaculty(@RequestParam String email,
                             @RequestParam String password,
                             HttpSession session) {
        Faculty faculty = authService.authenticateFaculty(email, password);
        if(faculty != null) {
            session.setAttribute("facultyId", faculty.getId());
            return "redirect:/faculty/dashboard";
        }
        return "redirect:/auth/faculty-login?error";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    // Add to existing AuthController class
@GetMapping("/student-login")
public String showStudentLoginForm() {
    return "auth/student-login";
}

@PostMapping("/student-login")
public String loginStudent(@RequestParam String email,
                         @RequestParam String password,
                         HttpSession session) {
    Student student = authService.authenticateStudent(email, password);
    if (student != null) {
        session.setAttribute("studentId", student.getId());
        return "redirect:/student/dashboard";
    }
    return "redirect:/auth/student-login?error";
}
// Add to existing AuthController
@GetMapping("/student-register")
public String showStudentRegisterForm(Model model) {
    model.addAttribute("student", new StudentRegistrationDto());
    return "auth/student-register";
}

@PostMapping("/student-register")
public String registerStudent(@ModelAttribute StudentRegistrationDto studentDto, 
                            HttpSession session) {
    Student student = new Student();
    student.setId(studentDto.getId());
    student.setName(studentDto.getName());
    student.setDepartment(studentDto.getDepartment());
    student.setMobile(studentDto.getMobile());
    student.setEmail(studentDto.getEmail());
    student.setPassword(studentDto.getPassword());

    if(authService.registerStudent(student)) {
        session.setAttribute("studentId", student.getId());
        return "redirect:/student/dashboard";
    }
    return "redirect:/auth/student-register?error";
}
}