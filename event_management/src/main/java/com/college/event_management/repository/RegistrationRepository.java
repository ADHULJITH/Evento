package com.college.event_management.repository;

import com.college.event_management.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByStudentIdAndEventId(String studentId, Long eventId);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Registration r WHERE r.student.id = ?1 AND r.event.id = ?2")
    void deleteByStudentIdAndEventId(String studentId, Long eventId);
    
    List<Registration> findByStudentId(String studentId);
    List<Registration> findByEventId(Long eventId); // Added for future use
}