package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.chasacademy.databaser.coursesystem.model.Course;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // Hämta kurs via titel
    Optional<Course> findByTitle(String title);
}
