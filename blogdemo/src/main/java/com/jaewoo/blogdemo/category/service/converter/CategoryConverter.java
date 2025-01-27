package com.jaewoo.blogdemo.category.service.converter;

import com.jaewoo.blogdemo.category.dto.CategoryResponse;
import com.jaewoo.blogdemo.category.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public CategoryResponse toDto(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
