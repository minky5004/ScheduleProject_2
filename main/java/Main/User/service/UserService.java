package Main.User.service;

import Main.User.dto.UserCreateRequest;
import Main.User.dto.UserCreateRespose;
import Main.User.entity.User;
import Main.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserCreateRespose save(UserCreateRequest request) {
        User user = new User(
                request.getUserName(),
                request.getEmail()
        );
        User savedUser = userRepository.save(user);
        return new UserCreateRespose(
            savedUser.getUserId(),
            savedUser.getUserName(),
            savedUser.getEmail(),
            savedUser.getCreatedAt(),
            savedUser.getModifiedAt()
        );
    }
}
