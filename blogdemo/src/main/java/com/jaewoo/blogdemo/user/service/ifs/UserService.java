package com.jaewoo.blogdemo.user.service.ifs;

import com.jaewoo.blogdemo.user.dto.ChangePasswordRequest;
import com.jaewoo.blogdemo.user.dto.CheckPasswordRequest;
import com.jaewoo.blogdemo.user.dto.LoginUserRequest;
import com.jaewoo.blogdemo.user.dto.RegisterUserRequest;
import com.jaewoo.blogdemo.user.entity.User;

public interface UserService {

    // 새로운 유저의 회원가입
    void register(RegisterUserRequest registerUserRequest);
    // 유저의 정보를 수정
    void changePassword(User user, ChangePasswordRequest request);
    // 유저의 정보를 조회
    void login(LoginUserRequest request);
    // 유저의 정보를 삭제
    void unregister();

    // 유저의 정보를 변경하기 전 비밀번호 체크
    void checkUserPassword(User user, CheckPasswordRequest request);
}
