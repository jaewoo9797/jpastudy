package com.jaewoo.blogdemo.article.db;

import com.jaewoo.blogdemo.article.entity.Article;
import com.jaewoo.blogdemo.article.entity.enums.ArticleStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a.id FROM Article a WHERE a.user.id = :userId")
    Page<Long> findArticleIdsByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT a FROM Article a "
            + "LEFT JOIN  a.comments "
            + "INNER JOIN FETCH a.user "
            + "WHERE a.id IN :ids"
    )
    List<Article> findArticlesWithDetailsByIds(@Param("ids") List<Long> ids);

    Optional<Article> findByIdAndUserId(Long articleId, Long userId);

    Optional<Article> findByIdAndStatus(Long articleId, ArticleStatus status);

    List<Article> findAllByStatus(ArticleStatus status, Pageable pageable);
}
