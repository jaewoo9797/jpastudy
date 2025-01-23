package com.jaewoo.blogdemo.user.db;

import com.jaewoo.blogdemo.article.db.ArticleRepository;
import com.jaewoo.blogdemo.article.entity.Article;
import com.jaewoo.blogdemo.common.config.JpaAuditingConfiguration;
import com.jaewoo.blogdemo.user.entity.Email;
import com.jaewoo.blogdemo.user.entity.User;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles("test")
@DataJpaTest
@Import({JpaAuditingConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryPageableTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ArticleRepository articleRepository;

    private User user;

    private Article article;

    @BeforeEach
    public void setUp() {
        user = User.create("hongtest", Email.of("hong@test.com"), "123123");
        userRepository.save(user);

        articleInit("test title", "test content");
    }

    private void articleInit(String title, String content) {
        for (int i = 1; i <= 30; i++) {
            article = Article.create(title + i, content + i, user);
            articleRepository.save(article);
        }
    }


}
