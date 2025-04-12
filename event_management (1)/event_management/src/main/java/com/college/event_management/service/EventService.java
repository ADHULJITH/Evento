package com.college.event_management.service;

import com.college.event_management.dto.EventDto;
import com.college.event_management.model.Event;
import com.college.event_management.model.Registration;
import com.college.event_management.repository.EventRepository;
import com.college.event_management.repository.FacultyRepository;
import com.college.event_management.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<Event> getEventsByFaculty(String facultyId) {
        return eventRepository.findByFacultyId(facultyId);
    }
    // Add to existing EventService class
public List<Event> getAllEvents() {
    return eventRepository.findAll();
}

public Event getEventById(Long id) {
    return eventRepository.findById(id).orElseThrow();
}

    public void createEvent(EventDto eventDto, String facultyId) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setVenue(eventDto.getVenue());
        event.setEventDate(LocalDate.parse(eventDto.getEventDate()));
        event.setEventTime(LocalTime.parse(eventDto.getEventTime()));
        event.setFaculty(facultyRepository.findById(facultyId).orElseThrow());
        eventRepository.save(event);
    }

    public List<Registration> getEventRegistrations(Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }
    

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}