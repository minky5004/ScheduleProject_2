package Main.Schdule.servuce;

import Main.Schdule.dto.ScheduleGetAllResponse;
import Main.Schdule.dto.SchedulePostRequest;
import Main.Schdule.dto.SchedulePostResponse;
import Main.Schdule.entity.Schedule;
import Main.Schdule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public SchedulePostResponse  create(SchedulePostRequest request) {

        Schedule schedule = new Schedule(
                request.getAuthor(),
                request.getTitle(),
                request.getContent()
        );

        Schedule saved = scheduleRepository.save(schedule);

        return  new SchedulePostResponse(
                saved.getScheduleId(),
                saved.getAuthor(),
                saved.getTitle(),
                saved.getContent(),
                saved.getCreatedAt(),
                saved.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleGetAllResponse> findALl(String author) {
        // 조회시
        if (author == null) {
            List<Schedule> getSchedules = scheduleRepository.findAll();

            List<ScheduleGetAllResponse> dtos = new ArrayList<>();

            for (Schedule schedule : getSchedules) {
                dtos.add(new ScheduleGetAllResponse(
                        schedule.getScheduleId(),
                        schedule.getAuthor(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                ));
            }
        }
        List<Schedule> getScheduleAuthor = scheduleRepository.findAllByAuthor(author)
    }
}
