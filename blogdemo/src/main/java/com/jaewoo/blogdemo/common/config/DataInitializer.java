package com.jaewoo.blogdemo.common.config;

import com.jaewoo.blogdemo.category.db.CategoryRepository;
import com.jaewoo.blogdemo.category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final CategoryRepository categoryRepository;

    private void categorySetup() {
        for (int i = 1; i <= 5; i++) {
            String categoryName = "Category ";
            Category category = Category.of(categoryName + i);
            categoryRepository.save(category);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        categorySetup();
    }
}
