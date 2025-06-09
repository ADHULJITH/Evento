package com.college.event_management.controller;

import com.college.event_management.service.EventService;
import com.college.event_management.service.RegistrationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private EventService eventService;
    
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String studentId = (String) session.getAttribute("studentId");
        if (studentId == null) {
            return "redirect:/auth/student-login";
        }
        model.addAttribute("events", eventService.getAllEvents());
        return "student/dashboard";
    }

    @GetMapping("/my-events")
    public String myEvents(HttpSession session, Model model) {
        String studentId = (String) session.getAttribute("studentId");
        model.addAttribute("registrations", registrationService.getRegistrationsByStudent(studentId));
        return "student/my-events";
    }

    @PostMapping("/events/{eventId}/register")
    public String registerForEvent(@PathVariable Long eventId, HttpSession session) {
        String studentId = (String) session.getAttribute("studentId");
        registrationService.registerStudentForEvent(studentId, eventId);
        return "redirect:/student/dashboard";
    }

    @PostMapping("/events/{eventId}/cancel")
    public String cancelRegistration(@PathVariable Long eventId, HttpSession session) {
        String studentId = (String) session.getAttribute("studentId");
        registrationService.cancelRegistration(studentId, eventId);
        return "redirect:/student/my-events";
    }
}