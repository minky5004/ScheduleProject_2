package Main.User.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreateRequest {

    private String userName;
    private String email;
    @Size(min = 8)
    private String password;

}
