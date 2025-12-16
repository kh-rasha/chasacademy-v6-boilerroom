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

    // getters & setters
}
