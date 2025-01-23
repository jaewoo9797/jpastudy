package com.jaewoo.blogdemo.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequest(
        @NotBlank
        String username,
        @Email
        String email,
        @NotBlank   // TODO 어노테이션 만들어보기
        String password
) {
}
