package com.jaewoo.blogdemo.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUserRequest(
        @NotBlank
        String username,
        @Email
        String email,
        @NotBlank
        String password
) {
}
