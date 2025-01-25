package com.jaewoo.blogdemo.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCommentRequest(
        @NotNull
        Long articleId,
        @NotBlank
        String comment
) {
}
