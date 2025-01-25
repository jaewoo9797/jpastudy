package com.jaewoo.blogdemo.article.dto;

import jakarta.validation.constraints.NotBlank;

public record WriteArticleRequest(
        @NotBlank
        String title,
        @NotBlank
        String content
) {
}
/**
 * title,
 * content
 */