package com.jaewoo.blogdemo.article.db;

import com.jaewoo.blogdemo.article.entity.Article;
import com.jaewoo.blogdemo.common.config.JpaAuditingConfiguration;
import com.jaewoo.blogdemo.user.db.UserRepository;
import com.jaewoo.blogdemo.user.entity.Email;
import com.jaewoo.blogdemo.user.entity.User;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@Slf4j
@ActiveProfiles("test")
@DataJpaTest
@Import(JpaAuditingConfiguration.class)
@TestPropertySource(locations = {"classpath*:application.yml"})
public class ArticleUserPaginationTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    private User user;

    private Article article;

    @BeforeEach
    public void setUp() {
        user = User.create("hongtest", Email.of("hong@test.com"), "123123");
        userRepository.save(user);

        articleInit("test title", "test content");
    }

    private void articleInit(String title, String content) {
        for (int i = 1; i<= 30; i++) {
            article = Article.create(title + i, content + i, user);
            articleRepository.save(article);
        }
    }

    @Test
    void 유저_자신이_쓴_게시글_조회_페이징() {
        // given
        Pageable pageable = PageRequest.of(2,10);
        // when
        entityManager.flush();
        entityManager.clear();
        System.out.println("===============================");
        List<Article> articles = articleRepository.findUserWriteArticlesByUserIdWithPageable(user.getId(), pageable);
        // then
        articles.forEach(it -> {
            log.info("title = {}, id = {}, username = {}", it.getTitle(), it.getId(), it.getUser().getUsername());
        });
    }
}
