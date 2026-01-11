package Main.User.contoller;

import Main.User.dto.*;
import Main.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("users")
    public ResponseEntity<UserCreateRespose> save(
            @RequestBody UserCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

}
