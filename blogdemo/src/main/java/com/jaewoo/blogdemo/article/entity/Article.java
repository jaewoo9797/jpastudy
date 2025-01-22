package com.jaewoo.blogdemo.article.entity;

import com.jaewoo.blogdemo.common.baseentity.BaseEntity;
import com.jaewoo.blogdemo.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class Article extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 55)
    private String title;

    @Column(nullable = false)   // TODO 텍스트 컬럼이라는게 있었음. 글자 수가 많을 경우
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public static Article create(String title, String content, User author) {

        return Article.builder()
                .title(title)
                .content(content)
                .user(author)
                .build();
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
