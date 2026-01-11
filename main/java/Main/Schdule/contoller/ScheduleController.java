package Main.Schdule.contoller;

import Main.Schdule.dto.*;
import Main.Schdule.servuce.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleservice;

    // 일정 등록
    @PostMapping("/schedules")
    public ResponseEntity<SchedulePostResponse> create(
            @RequestBody SchedulePostRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleservice.create(request));
    }

    // 전체 조회 (작성 유저명을 기준으로 전체 조회)
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetAllResponse>> getAll(
            @RequestParam(required = false) String author
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleservice.findALl(author));
    }

    // 단일 조회 (스케쥴 ID를 통한 단건 조회)
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleGetResponse> get(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleservice.findOne(scheduleId));
    }
}
