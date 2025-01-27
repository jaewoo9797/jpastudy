package com.jaewoo.blogdemo.category.service.ifs;

import com.jaewoo.blogdemo.category.dto.CategoryNameAndCountArticle;
import java.util.List;

public interface CategoryQueryService {

    List<CategoryNameAndCountArticle> findCategoryNameAndCountArticle();
}
