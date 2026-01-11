package Main.User.dto;

import lombok.Getter;

@Getter
public class UserUpdateRequest {

    private Long userId;
    private String userName;
    private String email;

}