package se.chasacademy.databaser.coursesystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Id;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String title;

    private String description;

    @Min(1)
    @Max(50)
    @Column(nullable = false)
    private int maxParticipants;

    // M:1 (många kurser -> en lärare)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    // 1:M (en kurs -> många kurstillfällen)
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseSession> sessions = new ArrayList<>();

    // M:N (en kurs <-> många deltagare)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_participants",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private Set<Participant> participants = new HashSet<>();

    public Course() {}

    public Course(String title, String description, int maxParticipants, Teacher teacher) {
        this.title = title;
        this.description = description;
        this.maxParticipants = maxParticipants;
        this.teacher = teacher;
    }
    // Kolla fullbokad
    public boolean isFullBooked() {
        return participants.size() >= maxParticipants;
    }

    // Getters/Setters

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(int maxParticipants) { this.maxParticipants = maxParticipants; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public List<CourseSession> getSessions() { return sessions; }

    public Set<Participant> getParticipants() { return participants; }
}

