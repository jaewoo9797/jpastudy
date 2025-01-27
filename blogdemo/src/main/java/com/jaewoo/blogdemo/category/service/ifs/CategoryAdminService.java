package com.jaewoo.blogdemo.category.service.ifs;

import com.jaewoo.blogdemo.category.dto.CategoryResponse;
import com.jaewoo.blogdemo.category.dto.CreateCategoryRequest;
import com.jaewoo.blogdemo.category.dto.DeleteCategoryRequest;
import com.jaewoo.blogdemo.category.dto.UpdateCategoryRequest;

public interface CategoryAdminService {

    CategoryResponse createCategory(CreateCategoryRequest request);

    CategoryResponse updateCategory(UpdateCategoryRequest request);

    void deleteCategory(DeleteCategoryRequest request);
}
