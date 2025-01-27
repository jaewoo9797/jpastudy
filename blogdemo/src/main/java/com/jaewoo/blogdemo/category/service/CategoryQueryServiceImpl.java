package com.jaewoo.blogdemo.category.service;

import com.jaewoo.blogdemo.category.db.CategoryRepository;
import com.jaewoo.blogdemo.category.dto.CategoryNameAndCountArticle;
import com.jaewoo.blogdemo.category.dto.CategoryNameAndCountArticleResponse;
import com.jaewoo.blogdemo.category.dto.CategoryResponse;
import com.jaewoo.blogdemo.category.entity.Category;
import com.jaewoo.blogdemo.category.service.converter.CategoryConverter;
import com.jaewoo.blogdemo.category.service.ifs.CategoryQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter converter;

    @Override
    public List<CategoryResponse> findCategoryAll() {
        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream()
                .map(converter::toDto)
                .toList();
    }

    @Override
    public List<CategoryNameAndCountArticle> findCategoryNameAndCountArticle() {
        return categoryRepository.findCategoryNameAndArticleCount();
    }

}
