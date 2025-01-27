package com.jaewoo.blogdemo.category.controller;

import com.jaewoo.blogdemo.category.dto.CategoryNameAndCountArticle;
import com.jaewoo.blogdemo.category.service.ifs.CategoryQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/category")
public class CategoryReadController {

    private final CategoryQueryService categoryReadService;

    @GetMapping("/all-count")
    public ResponseEntity<List<CategoryNameAndCountArticle>> findAllCount() {
        List<CategoryNameAndCountArticle> categoryNameAndCountArticle = categoryReadService.findCategoryNameAndCountArticle();
        return ResponseEntity.ok(categoryNameAndCountArticle);
    }
}
