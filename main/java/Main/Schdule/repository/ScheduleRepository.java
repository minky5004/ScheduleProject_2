package Main.Schdule.repository;

import Main.Schdule.entity.Schedule;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByAuthor(String author);
}
