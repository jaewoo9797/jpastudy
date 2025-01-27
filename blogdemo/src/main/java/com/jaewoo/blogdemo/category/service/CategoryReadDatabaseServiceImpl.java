package com.jaewoo.blogdemo.category.service;

import com.jaewoo.blogdemo.category.db.CategoryRepository;
import com.jaewoo.blogdemo.category.dto.CategoryResponse;
import com.jaewoo.blogdemo.category.entity.Category;
import com.jaewoo.blogdemo.category.service.converter.CategoryConverter;
import com.jaewoo.blogdemo.category.service.ifs.CategoryReadService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryReadDatabaseServiceImpl implements CategoryReadService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter converter;
    @Override
    public List<CategoryResponse> findCategoryAll() {
        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream()
                .map(converter::toDto)
                .toList();
    }

}
