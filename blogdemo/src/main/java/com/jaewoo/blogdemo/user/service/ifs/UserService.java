package com.jaewoo.blogdemo.user.service.ifs;

public interface UserService {

    // 새로운 유저의 회원가입
    void register();
    // 유저의 정보를 수정
    void changePassword();
    // 유저의 정보를 조회
    void login();
    // 유저의 정보를 삭제
    void unregister();

    // 유저의 정보를 변경하기 전 비밀번호 체크
    void checkUserPassword();
}
