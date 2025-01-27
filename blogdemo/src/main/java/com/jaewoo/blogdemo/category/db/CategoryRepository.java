package com.jaewoo.blogdemo.category.db;

import com.jaewoo.blogdemo.category.dto.CategoryNameAndCountArticle;
import com.jaewoo.blogdemo.category.entity.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    @Query("SELECT c.name AS categoryName, COUNT(a) AS articleCount "
            + "FROM Category c LEFT JOIN c.articles a "
            + "GROUP BY c.name")
    List<CategoryNameAndCountArticle> findCategoryNameAndArticleCount();
}
