package Main.User.contoller;

import Main.User.dto.*;
import Main.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserCreateResponse> save(
            @RequestBody UserCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserGetAllResponse>> GetAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserGetResponse> get(
            @PathVariable Long userId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findOne(userId));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserUpdateResponse> update(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userId,request));
    }

    @DeleteMapping("/udsers/{userId}")
    public void delete(
            @PathVariable Long userId
    ) {
        userService.delete(userId);
    }

}
