package Main.User.service;

import Main.User.dto.*;
import Main.User.entity.User;
import Main.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserCreateResponse save(UserCreateRequest request) {
        User user = new User(
                request.getUserName(),
                request.getEmail(),
                request.getPassword()
        );
        User savedUser = userRepository.save(user);
        return new UserCreateResponse(
            savedUser.getUserId(),
            savedUser.getUserName(),
            savedUser.getEmail(),
            savedUser.getCreatedAt(),
            savedUser.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<UserGetAllResponse> findAll() {

        List<User> savedUsers = userRepository.findAll();

        List<UserGetAllResponse> dtos = new ArrayList<>();

        for (User user : savedUsers) {
            dtos.add(new UserGetAllResponse(
                    user.getUserId(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt()
            ));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public UserGetResponse findOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다")
        );
        return new UserGetResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public UserUpdateResponse update(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다")
        );
        user.update(
                request.getUserName(),
                request.getEmail()
        );
        return new UserUpdateResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public void delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다")
        );
        userRepository.deleteById(userId);
    }

    @Transactional
    public UserLoginResponse login(String email, String userPassword) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );
        if (!user.getUserPassword().equals(userPassword)) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다");
        }

        return new UserLoginResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }
}
