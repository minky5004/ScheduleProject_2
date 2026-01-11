package Main.Schdule.servuce;

import Main.Schdule.dto.*;
import Main.Schdule.entity.Schedule;
import Main.Schdule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 등록
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

    // 전체 조회 (작성 유저명을 기준으로 전체 조회)
    @Transactional(readOnly = true)
    public List<ScheduleGetAllResponse> findALl(String author) {
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
        List<Schedule> getScheduleAuthor = scheduleRepository.findAllByAuthor(author);

        List<ScheduleGetAllResponse> dtos = new ArrayList<>();

        for (Schedule schedule : getScheduleAuthor) {
            dtos.add(new ScheduleGetAllResponse(
                    schedule.getScheduleId(),
                    schedule.getAuthor(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            ));
        }
        return dtos;
    }

    // 단일 조회 (스케쥴 ID를 통한 단건 조회)
    @Transactional(readOnly = true)
    public ScheduleGetResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("입력하신 정보와 일치하는 일정이 없습니다.")
        );
        return new ScheduleGetResponse(
                schedule.getScheduleId(),
                schedule.getAuthor(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 수정 기능
    @Transactional
    public ScheduleUpdateResponse update(Long scheduleId, ScheduleUpdateRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("입력하신 정보와 일치하는 일정이 없습니다.")
        );
        schedule.update(
                request.getAuthor(),
                request.getTitle(),
                request.getContent()
        );
        return new ScheduleUpdateResponse(
                schedule.getScheduleId(),
                schedule.getAuthor(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 삭제 기능
    @Transactional
    public void delete(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("입력하신 정보와 일치하는 일정이 없습니다.")
        );

    }
}
