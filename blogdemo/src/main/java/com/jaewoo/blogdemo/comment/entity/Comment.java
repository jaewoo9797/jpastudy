package com.jaewoo.blogdemo.comment.entity;

import com.jaewoo.blogdemo.article.entity.Article;
import com.jaewoo.blogdemo.common.baseentity.BaseEntity;
import com.jaewoo.blogdemo.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)   // columnDefinition = "TEXT" 에대해서 알아보기
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "article_id")
    private Article article;

    public static Comment create(String content, User writer, Article article) {

        return Comment.builder()
                .content(content)
                .writer(writer)
                .article(article)
                .build();
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
