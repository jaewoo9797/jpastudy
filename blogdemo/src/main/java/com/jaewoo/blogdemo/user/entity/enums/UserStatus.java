package com.jaewoo.blogdemo.user.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    private final String msg;
}
