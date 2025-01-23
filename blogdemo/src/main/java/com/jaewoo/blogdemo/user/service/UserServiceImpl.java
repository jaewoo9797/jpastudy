package com.jaewoo.blogdemo.user.service;

import com.jaewoo.blogdemo.common.auth.Encryptor;
import com.jaewoo.blogdemo.user.db.UserRepository;
import com.jaewoo.blogdemo.user.entity.Email;
import com.jaewoo.blogdemo.user.entity.User;
import com.jaewoo.blogdemo.user.service.ifs.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Encryptor encryptor;

    /**
     *  새로운 유저의 생성.
     *  1. 유저의 이름, 이메일, 비밀번호를 입력받는다.
     *  2. 유저의 비밀번호를 암호화한다.
     *  3. 디비에 저장한다.
     */
    @Override
    public void register() {
        // TODO 비밀번호 검증, 파라미터로 값 넘겨받기, converter 생각하기
        User registerUser = toEntity("test", "123123", "hong@mail.com");
        userRepository.save(registerUser);
    }

    /**
     *  두 가지 방법이 생각남
     *  1. 유저가 세션에 저장되어 있다면, 바로 디비에 save 새로운 비밀번호
     *  2. 디비에서 조회 후 비밀번호를 변경하고 저장.
     *  디비에 한 번 다녀오는건 좋아보이지 않음 현재 로그인 된 상태라면 세션, jwt 등 다른 방법이 있을 것 같음
     *  구현한 건 디비를 두 번 접속하는데, 줄일 수 있는 방법이 있지않을까?
     */
    @Override
    @Transactional
    public void changePassword() {
        // 일단 조회 후 변경
        User foundUser = userRepository.findById(1L)
                .filter(it -> encryptor.matches("inputValue", it.getEncryptedPassword()))
                .orElseThrow(IllegalArgumentException::new);

        foundUser.changePassword(encryptor.encrypt("newPassword"));
    }

    /**
     *   1. 이메일, 비밀번호로 디비에 조회할 것 같음
     *   2. 성공하면, 세션에 저장하던지, jwt 토큰을 반환해주는 방법이 생각남
     *   3. 여기서 어떻게 처리하느냐에 따라서 조회 등 변경 처리로직이 변경될 것 같음
     */
    @Override
    public void login() {
        User founduser = userRepository.findByEmail(Email.of("test@mail.com"))
                .filter(it -> encryptor.matches("inputValue", it.getEncryptedPassword()))
                .orElseThrow(IllegalArgumentException::new);
        // 특별 로직 추가해서 로그인 기능 구현하기
    }

    /**
     *  1. 유저의 비밀번호를 재입력 받기
     *  2. 회원탈퇴하는 이유 제공받을 수 있음
     *  3. 소프트 삭제로 변경 가능
     */
    @Override
    public void unregister() {
        userRepository.deleteById(1L);
    }

    /**
     *  유저가 자신의 정보를 수정하기 위해서 거쳐야하는 비밀번호 체크
     *  1. 유저가 비밀번호 체크를 했는지 안했는지 어떻게 검증할건지?
     *  2. 이후 변경 시 비밀번호, 유저이름 등 한번에 동적으로 수정하려고할 때 쿼리 만들어야겠네
     */
    @Override
    public void checkUserPassword() {
        User founduser = userRepository.findByEmail(Email.of("test@mail.com"))
                .filter(it -> encryptor.matches("inputValue", it.getEncryptedPassword()))
                .orElseThrow(IllegalArgumentException::new);
    }

    private User toEntity(String username, String password, String email) {
        Email inputEmail = Email.of(email);
        String encryptedPassword = encryptor.encrypt(password);
        return User.create(username, inputEmail, encryptedPassword);
    }
}
