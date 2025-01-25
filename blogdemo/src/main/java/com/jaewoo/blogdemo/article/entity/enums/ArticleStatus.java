package com.jaewoo.blogdemo.article.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  리팩터링 대상
 *  모든 엔티티에 status 상태가 있는데, 이거 어떻게 해결해볼지 고민해보기
 */
@AllArgsConstructor
@Getter
public enum ArticleStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    private final String state;

}
