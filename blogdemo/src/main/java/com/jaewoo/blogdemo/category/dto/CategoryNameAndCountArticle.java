package com.jaewoo.blogdemo.category.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public interface CategoryNameAndCountArticle {
    String getCategoryName();
    int getArticleCount();
}
