package Main.Schdule.contoller;

import Main.Schdule.dto.*;
import Main.Schdule.servuce.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 전체 조회
    @GetMapping("/schedules")
    public ResponseEntity<ScheduleGetAllResponse> getAll(
            @RequestParam(required = false) String author
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleservice.findALl(author));
    }

}
