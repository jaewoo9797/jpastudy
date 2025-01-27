package com.jaewoo.blogdemo.category.controller;

import com.jaewoo.blogdemo.category.dto.CategoryResponse;
import com.jaewoo.blogdemo.category.service.ifs.CategoryReadService;
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

    private final CategoryReadService categoryReadService;

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<CategoryResponse> categoryAll = categoryReadService.findCategoryAll();
        return ResponseEntity.ok(categoryAll);
    }
}
