package com.jaewoo.blogdemo.category.dto;

import jakarta.validation.constraints.NotNull;

public record DeleteCategoryRequest(
        @NotNull Long id
) {
}
