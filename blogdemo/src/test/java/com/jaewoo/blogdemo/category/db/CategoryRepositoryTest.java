package com.jaewoo.blogdemo.category.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jaewoo.blogdemo.category.entity.Category;
import com.jaewoo.blogdemo.common.config.JpaAuditingConfiguration;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
@Import({JpaAuditingConfiguration.class})
class CategoryRepositoryTest {
    private static final String CATEGORY_NAME = "test";
    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @Autowired
    private EntityManager em;

    @Test
    void 카테고리_저장() {
        // given
        category = Category.of(CATEGORY_NAME);
        // when
        Category saved = categoryRepository.save(category);
        // then
        assertThat(saved.getName()).isEqualTo(CATEGORY_NAME);
        assertThat(saved.getId()).isGreaterThan(0);
    }

    @Nested
    @DisplayName("카테고리 조회, 수정, 삭제")
    class WhenSave {

        @BeforeEach
        void setUp() {
            category = Category.of(CATEGORY_NAME);
            categoryRepository.save(category);
        }

        @Test
        void 카테고리_조회() {
            // given

            // when
            Optional<Category> foundCategory = categoryRepository.findByName(CATEGORY_NAME);
            // then
            assertThat(foundCategory.isPresent()).isTrue();
        }

        @Test
        void 카테고리_모두_찾기() {
            // given
            Category additionalCategory = Category.of("additional");
            categoryRepository.save(additionalCategory);
            // when
            List<Category> categoryList = categoryRepository.findAll();
            // then
            assertThat(categoryList.size()).isGreaterThan(1);
        }

        @Test
        void 카테고리_정보_수정() {
            // given
            String newCategoryName = "newCategory";
            Optional<Category> foundCategory = categoryRepository.findByName(CATEGORY_NAME);
            // when
            foundCategory.ifPresent(it -> it.changeName(newCategoryName));
            em.flush();
            em.close();
            Optional<Category> oldNameCategory = categoryRepository.findByName(CATEGORY_NAME);
            // then
            assertThatThrownBy(oldNameCategory::get)
                    .isInstanceOf(NoSuchElementException.class);
        }

        @Test
        void 카테고리_삭제() {
            // given

            // when

            // then
        }
    }
}