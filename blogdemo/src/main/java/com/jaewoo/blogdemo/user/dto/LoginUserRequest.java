package com.jaewoo.blogdemo.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUserRequest(
        @Email
        String email,
        @NotBlank
        String password
) {
}
