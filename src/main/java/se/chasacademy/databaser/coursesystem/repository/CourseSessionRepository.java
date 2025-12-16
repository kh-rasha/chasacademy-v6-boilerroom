package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.chasacademy.databaser.coursesystem.model.CourseSession;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseSessionRepository extends JpaRepository<CourseSession, Long> {
    List<CourseSession> findByDateAfter(LocalDateTime date);
    List<CourseSession> findByRoomName(String roomName);
    List<CourseSession> findByRoomId(Long roomId);
}
