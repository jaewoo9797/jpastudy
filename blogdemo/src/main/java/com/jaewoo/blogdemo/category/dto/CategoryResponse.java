package com.jaewoo.blogdemo.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CategoryResponse(
        @NotNull Long id,
        @NotBlank String name
) {
}
