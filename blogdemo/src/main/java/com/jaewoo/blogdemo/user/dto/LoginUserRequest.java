package com.jaewoo.blogdemo.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUserRequest(
        @Email
        String email,
        @NotBlank
        String password
        // TODO 로그인 할 때 굳이 커스텀 어노테이션으로 값 검증을 할 필요는 없을 것 같다. 그냥 비밀번호가 틀렸다고 반환하면 될것 같음
) {
}
