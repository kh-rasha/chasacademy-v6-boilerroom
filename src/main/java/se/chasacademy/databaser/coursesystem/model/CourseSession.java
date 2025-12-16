package se.chasacademy.databaser.coursesystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "course_sessions")
public class CourseSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable=false)
    private LocalDateTime date;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Room room;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public LocalDateTime getDate() {return date;}
    public void setDate(LocalDateTime date) {this.date = date;}
    public Course getCourse() {return course;}
    public void setCourse(Course course) {this.course = course;}
    public Room getRoom() {return room;}
    public void setRoom(Room room) {this.room = room;}
}
