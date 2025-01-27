package com.jaewoo.blogdemo.article.entity;

import com.jaewoo.blogdemo.article.entity.enums.ArticleStatus;
import com.jaewoo.blogdemo.category.entity.Category;
import com.jaewoo.blogdemo.comment.entity.Comment;
import com.jaewoo.blogdemo.common.baseentity.BaseEntity;
import com.jaewoo.blogdemo.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@ToString
public class Article extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 55)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> comments = new ArrayList<>();

    public static Article create(String title, String content, User author) {
        return Article.builder()
                .title(title)
                .status(ArticleStatus.REGISTERED)
                .content(content)
                .user(author)
                .build();
    }

    public static Article create(String title, String content, User user, Category category) {

        return Article.builder()
                .title(title)
                .status(ArticleStatus.REGISTERED)
                .content(content)
                .user(user)
                .category(category)
                .build();
    }

    public static Article createForUpdate(Long articleId, String title, String content) {

        return Article.builder()
                .id(articleId)
                .title(title)
                .content(content)
                .build();
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void updateArticle(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void unregister() {
        this.status = ArticleStatus.UNREGISTERED;
    }

    public boolean isRegistered() {
        return this.status == ArticleStatus.REGISTERED;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setArticle(this);
    }

}
