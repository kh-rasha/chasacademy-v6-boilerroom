package se.chasacademy.databaser.coursesystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String address;

    @Min(1)
    @Column(nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<CourseSession> sessions = new ArrayList<>();

    // ===== Getters =====

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<CourseSession> getSessions() {
        return sessions;
    }

    // ===== Setters =====

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setSessions(List<CourseSession> sessions) {
        this.sessions = sessions;
    }
}
