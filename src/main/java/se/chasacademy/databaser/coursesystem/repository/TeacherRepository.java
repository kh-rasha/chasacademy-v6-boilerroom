package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.chasacademy.databaser.coursesystem.model.Teacher;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("""
        SELECT t FROM Teacher t
        WHERE SIZE(t.courses) > :count
    """)
    List<Teacher> findTeachersWithMoreThanNCourses(@Param("count") long count);
}
