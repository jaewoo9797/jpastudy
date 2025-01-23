package com.jaewoo.blogdemo.article.db;

import com.jaewoo.blogdemo.article.entity.Article;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(
             "SELECT a "
             + "FROM Article a "
            + "INNER JOIN FETCH a.user "
            + "WHERE a.user.id = :userId"
    )
    List<Article> findUserWriteArticlesByUserIdWithPageable(@Param("userId") Long userId, Pageable pageable);

}
