package com.jaewoo.blogdemo.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequest(
        @NotBlank
        String username,
        @Email
        String email,
        @NotBlank
        String password
) {
}
