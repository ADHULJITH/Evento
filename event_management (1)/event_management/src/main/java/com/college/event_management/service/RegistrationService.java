package com.college.event_management.service;

import com.college.event_management.model.Registration;
import com.college.event_management.repository.RegistrationRepository;
import com.college.event_management.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;
    private final EventService eventService;

    public RegistrationService(RegistrationRepository registrationRepository,
                             StudentRepository studentRepository,
                             EventService eventService) {
        this.registrationRepository = registrationRepository;
        this.studentRepository = studentRepository;
        this.eventService = eventService;
    }

    @Transactional
    public void registerStudentForEvent(String studentId, Long eventId) {
        if (!registrationRepository.existsByStudentIdAndEventId(studentId, eventId)) {
            Registration registration = new Registration();
            registration.setStudent(studentRepository.findById(studentId).orElseThrow());
            registration.setEvent(eventService.getEventById(eventId));
            registrationRepository.save(registration);
        }
    }

    @Transactional
    public void cancelRegistration(String studentId, Long eventId) {
        registrationRepository.deleteByStudentIdAndEventId(studentId, eventId);
    }

    public List<Registration> getRegistrationsByStudent(String studentId) {
        return registrationRepository.findByStudentId(studentId);
    }
    
    public List<Registration> getRegistrationsForEvent(Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }
}