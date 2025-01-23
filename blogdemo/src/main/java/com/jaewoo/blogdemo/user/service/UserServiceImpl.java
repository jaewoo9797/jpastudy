package com.jaewoo.blogdemo.user.service;

import com.jaewoo.blogdemo.common.auth.Encryptor;
import com.jaewoo.blogdemo.user.db.UserRepository;
import com.jaewoo.blogdemo.user.dto.ChangePasswordRequest;
import com.jaewoo.blogdemo.user.dto.CheckPasswordRequest;
import com.jaewoo.blogdemo.user.dto.LoginUserRequest;
import com.jaewoo.blogdemo.user.dto.RegisterUserRequest;
import com.jaewoo.blogdemo.user.entity.Email;
import com.jaewoo.blogdemo.user.entity.User;
import com.jaewoo.blogdemo.user.service.ifs.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Encryptor encryptor;
    // 유저 객체를 principle 등 어떻게 객체로 받아온다고 가정하고 코딩해보자.

    @Override
    public void register(RegisterUserRequest request) {

        User registerUser =
                toEntity(request.username(), encryptor.encrypt(request.password()), request.email());

        User savedUser = userRepository.save(registerUser);
        // TODO 저장 후 저장된 객체를 리턴해야 하는 이유 알아보자
        // 내 생각에는 세션이나 등록?
    }


    @Override
    public void changePassword(User user, ChangePasswordRequest request) {
        user.changePassword(encryptor.encrypt(request.changePassword()));
        userRepository.save(user);
    }

    /**
     * 1. 이메일, 비밀번호로 디비에 조회할 것 같음 2. 성공하면, 세션에 저장하던지, jwt 토큰을 반환해주는 방법이 생각남 3. 여기서 어떻게 처리하느냐에 따라서 조회 등 변경 처리로직이 변경될 것
     * 같음
     */
    @Override
    @Transactional(readOnly = true)
    public void login(LoginUserRequest request) {
        User founduser = userRepository.findByEmail(Email.of(request.email()))
                .filter(it -> encryptor.matches(request.password(), it.getEncryptedPassword()))
                // TODO 유저 커스텀 예외 만들어서 예외 발생시키기
                .orElseThrow(IllegalArgumentException::new);
        // 로직 추가해서 로그인 기능 구현하기

    }

    /**
     * 1. 유저의 비밀번호를 재입력 받기 2. 회원탈퇴하는 이유 제공받을 수 있음 3. 소프트 삭제로 변경 가능
     */
    @Override
    public void unregister() {
        userRepository.deleteById(1L);
    }

    /**
     * 유저가 자신의 정보를 수정하기 위해서 거쳐야하는 비밀번호 체크 1. 유저가 비밀번호 체크를 했는지 안했는지 어떻게 검증할건지? 2. 이후 변경 시 비밀번호, 유저이름 등 한번에 동적으로 수정하려고할 때
     * 쿼리 만들어야겠네
     */
    @Override
    public void matchUserPassword(User user, CheckPasswordRequest request) {
        boolean matchesResult = encryptor.matches(request.password(), user.getEncryptedPassword());
        if (!matchesResult) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }
    }


    @Override
    public void findUserWritingArticleWithPagination(User user, Pageable pageable) {
        Long id = user.getId();

    }

    private User toEntity(String username, String password, String email) {
        Email inputEmail = Email.of(email);
        String encryptedPassword = encryptor.encrypt(password);
        return User.create(username, inputEmail, encryptedPassword);
    }
}
