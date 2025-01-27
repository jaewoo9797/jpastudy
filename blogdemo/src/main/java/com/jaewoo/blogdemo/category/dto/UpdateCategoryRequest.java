package com.jaewoo.blogdemo.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCategoryRequest(
        @NotNull Long id,
        @NotBlank String oldName,
        @NotBlank String newName
) {
}
