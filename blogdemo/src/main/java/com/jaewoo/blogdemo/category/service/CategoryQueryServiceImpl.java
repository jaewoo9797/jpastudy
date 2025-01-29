package com.jaewoo.blogdemo.category.service;

import com.jaewoo.blogdemo.category.db.CategoryRepository;
import com.jaewoo.blogdemo.category.dto.CategoryNameAndCountArticle;
import com.jaewoo.blogdemo.category.service.converter.CategoryConverter;
import com.jaewoo.blogdemo.category.service.ifs.CategoryQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter converter;

    @Override
    @Cacheable(value = "categories", key = "'all'")
    public List<CategoryNameAndCountArticle> findCategoryNameAndCountArticle() {
        return categoryRepository.findCategoryNameAndArticleCount();
    }

}
