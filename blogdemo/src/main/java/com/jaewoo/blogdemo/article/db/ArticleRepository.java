package com.jaewoo.blogdemo.article.db;

import com.jaewoo.blogdemo.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
