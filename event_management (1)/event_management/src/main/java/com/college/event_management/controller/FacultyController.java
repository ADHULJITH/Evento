package com.college.event_management.controller;

import com.college.event_management.dto.EventDto;
import com.college.event_management.service.EventService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private EventService eventService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String facultyId = (String) session.getAttribute("facultyId");
        if (facultyId == null) {
            return "redirect:/auth/faculty-login";
        }
        model.addAttribute("events", eventService.getEventsByFaculty(facultyId));
        return "faculty/dashboard";
    }

    @GetMapping("/events/create")
    public String showCreateForm(Model model) {
        model.addAttribute("event", new EventDto());
        return "faculty/create-event";
    }

    @PostMapping("/events/create")
    public String createEvent(@ModelAttribute EventDto eventDto, HttpSession session) {
        String facultyId = (String) session.getAttribute("facultyId");
        eventService.createEvent(eventDto, facultyId);
        return "redirect:/faculty/dashboard";
    }

    @GetMapping("/events/{id}/registrations")
    public String viewRegistrations(@PathVariable Long id, Model model) {
        model.addAttribute("registrations", eventService.getEventRegistrations(id));
        model.addAttribute("eventId", id); // Add event ID to model
        return "faculty/view-registrations";
    }

    @PostMapping("/events/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/faculty/dashboard";
    }
}