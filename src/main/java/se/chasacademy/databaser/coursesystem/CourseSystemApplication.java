package se.chasacademy.databaser.coursesystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.chasacademy.databaser.coursesystem.model.*;
import se.chasacademy.databaser.coursesystem.repository.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class CourseSystemApplication implements CommandLineRunner {
    private final TeacherRepository teacherRepo;
    private final CourseRepository courseRepo;
    private final ParticipantRepository participantRepo;
    private final RoomRepository roomRepo;
    private final CourseSessionRepository sessionRepo;

    public CourseSystemApplication(
            TeacherRepository teacherRepo,
            CourseRepository courseRepo,
            ParticipantRepository participantRepo,
            RoomRepository roomRepo,
            CourseSessionRepository sessionRepo) {

        this.teacherRepo = teacherRepo;
        this.courseRepo = courseRepo;
        this.participantRepo = participantRepo;
        this.roomRepo = roomRepo;
        this.sessionRepo = sessionRepo;
        }

	public static void main(String[] args) {
		SpringApplication.run(CourseSystemApplication.class, args);
	}

	@Override
	public void run(String... args) {

        // --------------------
        // 1. Skapa grunddata
        // --------------------

        Teacher teacher = new Teacher();
        teacher.setFirstName("Anna");
        teacher.setLastName("Andersson");
        teacher.setEmail("anna@chas.se");
        TeacherRepository.save(teacher);

        Course course1 = new Course();
        course1.setTitle("Java Grundkurs");
        course1.setMaxParticipants(10);
        course1.setTeacher(teacher);

        Course course2 = new Course();
        course2.setTitle("Spring Boot");
        course2.setMaxParticipants(15);
        course2.setTeacher(teacher);

        CourseRepository.saveAll(List.of(course1, course2));

        Room room1 = new Room();
        room1.setName("Sal A");
        room1.setAddress("Campus 1");
        room1.setCapacity(10);

        Room room2 = new Room();
        room2.setName("Stora salen");
        room2.setAddress("Campus 2");
        room2.setCapacity(50);

        RoomRepository.saveAll(List.of(room1, room2));

        Participant p1 = new Participant();
        p1.setFullName("Kalle Karlsson");
        p1.setEmail("kalle@mail.se");

        Participant p2 = new Participant();
        p2.setFullName("Lisa Svensson");
        p2.setEmail("lisa@mail.se");

        ParticipantRepository.saveAll(List.of(p1, p2));

        // --------------------
        // 2. Relationer
        // --------------------

        course1.getParticipants().add(p1);
        course1.getParticipants().add(p2);
        CourseRepository.save(course1);

        CourseSession session1 = new CourseSession();
        session1.setCourse(course1);
        session1.setRoom(room1);
        session1.setDate(LocalDateTime.now().plusDays(5));

        CourseSession session2 = new CourseSession();
        session2.setCourse(course2);
        session2.setRoom(room2);
        session2.setDate(LocalDateTime.now().minusDays(2));

        CourseSessionRepository.saveAll(List.of(session1, session2));

        // --------------------
        // 3. Queries
        // --------------------

        System.out.println("\n📚 Kurser med lärare:");
        CourseRepository.findAll().forEach(c ->
                System.out.println(c.getTitle() + " - " + c.getTeacher().getFirstName())
        );

        System.out.println("\n👥 Deltagare i Java Grundkurs:");
        CourseRepository.findByTitle("Java Grundkurs").ifPresent(c ->
                c.getParticipants().forEach(p ->
                        System.out.println(p.getFullName()))
        );

        System.out.println("\n📅 Kommande kurstillfällen:");
        CourseSessionRepository.findByDateAfter(LocalDateTime.now())
                .forEach(s ->
                        System.out.println(
                                s.getCourse().getTitle() + " i " + s.getRoom().getName()
                        )
                );

        System.out.println("\n🏢 Lokaler med capacity > 20:");
        RoomRepository.findByCapacityGreaterThan(20)
                .forEach(r -> System.out.println(r.getName()));
    }
}
