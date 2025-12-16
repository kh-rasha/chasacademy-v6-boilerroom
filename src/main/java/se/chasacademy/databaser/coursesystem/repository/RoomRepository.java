package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.chasacademy.databaser.coursesystem.model.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    // 5) Rooms where capacity > X
    List<Room> findByCapacityGreaterThan(int capacity);
}
