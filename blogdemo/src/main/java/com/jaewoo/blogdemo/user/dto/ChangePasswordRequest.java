package com.jaewoo.blogdemo.user.dto;

import com.jaewoo.blogdemo.common.annotation.Password;

public record ChangePasswordRequest(
        @Password
        String changePassword
) {
}
