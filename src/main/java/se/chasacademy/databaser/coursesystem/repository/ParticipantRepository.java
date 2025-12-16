package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.chasacademy.databaser.coursesystem.model.Participant;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository <Participant, Long> {
    Optional<Participant> findByEmail(String email);

    boolean existsByEmail(String email);
}
