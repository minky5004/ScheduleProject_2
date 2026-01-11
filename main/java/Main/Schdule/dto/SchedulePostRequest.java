package Main.Schdule.dto;

import lombok.Getter;

@Getter
public class SchedulePostRequest {

    private Long scheduleId; // 일정 id
    private String author; // 작성 유저명
    private String title; // 할일 제목
    private String content; // 할일 내용

}
