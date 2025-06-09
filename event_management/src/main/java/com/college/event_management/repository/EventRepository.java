package com.college.event_management.repository;

import com.college.event_management.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByFacultyId(String facultyId);
}