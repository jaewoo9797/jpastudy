package com.jaewoo.blogdemo.comment.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommentStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    private final String state;

}
