package com.jaewoo.blogdemo.user.dto;

import jakarta.validation.constraints.NotBlank;

public record CheckPasswordRequest(
        @NotBlank
        String password
) {
}
