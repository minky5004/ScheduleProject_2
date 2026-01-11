package Main.Schdule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulePostResponse {

    private final Long scheduleId; // 일정 id
    private final String author; // 작성 유저명
    private final String title; // 할일 제목
    private final String content; // 할일 내용
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public SchedulePostResponse(Long scheduleId, String author, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.scheduleId = scheduleId;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
