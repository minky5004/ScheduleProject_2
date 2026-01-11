package Main.User.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserCreateRespose {

    private final Long userId;
    private final String userName;
    private final String email;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public UserCreateRespose(Long userId, String userName, String email, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
