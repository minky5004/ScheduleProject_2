package Main.Schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId; // 일정 id
    private String author; // 작성 유저명
    private String title; // 할일 제목
    private String content; // 할일 내용

    public Schedule(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void update(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
