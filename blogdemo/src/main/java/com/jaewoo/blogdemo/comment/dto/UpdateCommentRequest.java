package com.jaewoo.blogdemo.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCommentRequest(
        @NotNull
        Long articleId,
        @NotBlank
        String updateComment
) {
}
