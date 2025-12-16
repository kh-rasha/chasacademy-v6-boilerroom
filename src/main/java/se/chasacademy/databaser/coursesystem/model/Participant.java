package se.chasacademy.databaser.coursesystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(mappedBy = "participants")
    private Set<Course> courses = new HashSet<>();

    protected Participant() {
        // JPA
    }

    public Participant(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    // Optional: håll relationen synkad på båda sidor (om du vill)
    public void addCourse(Course course) {
        courses.add(course);
        course.getParticipants().add(this);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.getParticipants().remove(this);
    }
}