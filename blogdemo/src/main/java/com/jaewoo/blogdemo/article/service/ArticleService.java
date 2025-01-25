package com.jaewoo.blogdemo.article.service;

import com.jaewoo.blogdemo.article.dto.UpdateArticleRequest;
import com.jaewoo.blogdemo.article.dto.WriteArticleRequest;
import com.jaewoo.blogdemo.article.entity.Article;
import com.jaewoo.blogdemo.user.entity.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ArticleService {

    // 글 쓰기 - auth
    void writeArticle(User user, WriteArticleRequest request);

    // 글 조회 - every
    Article findArticleById(Long articleId);

    // 글 전체 조회 - every
    List<Article> findAllArticle(Pageable pageable);

    // 글 수정 - auth
    void updateArticle(User user, UpdateArticleRequest request);

    // 글 삭제 - auth
    void deleteArticle(User user, Long articleId);
}
