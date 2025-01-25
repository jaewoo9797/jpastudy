package com.jaewoo.blogdemo.article.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UpdateArticleRequest(
        @NotEmpty
        Long articleId,
        @NotBlank
        String updateTitle,
        @NotBlank
        String updateContent
) {
}
