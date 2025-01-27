package com.jaewoo.blogdemo.category.service.ifs;

import com.jaewoo.blogdemo.category.dto.CategoryResponse;
import java.util.List;

public interface CategoryReadService {

    List<CategoryResponse> findCategoryAll();
}
