package com.jaewoo.blogdemo.category.service.ifs;

import com.jaewoo.blogdemo.category.dto.CategoryNameAndCountArticle;
import com.jaewoo.blogdemo.category.dto.CategoryResponse;
import java.util.List;

public interface CategoryQueryService {

    List<CategoryResponse> findCategoryAll();

    List<CategoryNameAndCountArticle> findCategoryNameAndCountArticle();
}
