package com.jaewoo.blogdemo.article.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.jaewoo.blogdemo.article.entity.Article;
import com.jaewoo.blogdemo.common.config.JpaAuditingConfiguration;
import com.jaewoo.blogdemo.user.db.UserRepository;
import com.jaewoo.blogdemo.user.entity.Email;
import com.jaewoo.blogdemo.user.entity.User;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles("test")
@DataJpaTest
@Import(JpaAuditingConfiguration.class)
@TestPropertySource(locations = {"classpath*:application.yml"})
class ArticleRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    private Article article;

    @BeforeEach
    void setUp() {
        user = User.create("김길동", Email.of("kim@email.com"), "123123");
        userRepository.save(user);
        article = Article.create("test title", "test content", user);
    }

    @Test
    void 글을_생성한다() {
        // given
        User user = userRepository.save(
                User.create(
                        "홍길동",
                        Email.of("hong@mail.com"),
                        "123123")
        );
        String title = "test1";
        String content = "이 글이 제대로 테스트 동작하는지 확인합니다.";

        // when
        var createdArticle = Article.create(title, content, user);
        var savedArticle = articleRepository.save(createdArticle);

        // then
        Assertions.assertAll(
                () -> {
                    assertThat(savedArticle.getTitle()).isEqualTo(title);
                    assertThat(savedArticle.getContent()).isEqualTo(content);
                    assertThat(savedArticle.getUser()).isEqualTo(user);
                }
        );
    }

    @Test
    void 글을_저장한_후_모든_글_조회() {
        //given
        articleRepository.save(article);
        // when
        List<Article> articles = articleRepository.findAll();
        // then
        assertThat(articles).isNotEmpty();
        assertThat(articles.size()).isEqualTo(1);
    }

    @Test
    void 글의_제목을_수정하고_조회_변경된_제목으로_반환() {
        //given
        var savedArticle = articleRepository.save(article);
        String changeTitle = "change test title";
        // when
        savedArticle.changeTitle(changeTitle);
        var updatedArticle = articleRepository.save(savedArticle);
        // then
        assertThat(updatedArticle.getTitle()).isEqualTo(changeTitle);
    }

    @Test
    void 글의_내용을_수정하고_업데이트된_글_조회_반환() {
        //given
        var savedArticle = articleRepository.save(article);
        String changeContent = "change test content";
        // when
        savedArticle.changeContent(changeContent);
        var updatedArticle = articleRepository.save(savedArticle);
        // then
        assertThat(updatedArticle.getContent()).isEqualTo(changeContent);
    }

    @Test
    void 글을_삭제후_조회_시_비어있는_Optional반환() {
        //given
        articleRepository.save(article);
        // when
        articleRepository.deleteById(article.getId());
        Optional<Article> articleOptional = articleRepository.findById(article.getId());
        // then
        assertThat(articleOptional).isEmpty();
    }

    /**
     * 연관관계 테스트
     */
    // 1. Article 엔티티에서 user 필드를 통해 User 데이터를 저장하고 조회하는 테스트
    // 2. fetch 전략 Lazy, Eager 동작 방식 확인
    @Test
    void 글을_저장하고_조회후_유저의_정보에_대해서_접근() {
        //given
        articleRepository.save(article);

        // when
        entityManager.flush();
        entityManager.clear();

        Article fetchedArticle = articleRepository.findById(article.getId()).orElseThrow();

        // then
        assertThat(fetchedArticle.getUser().getUsername()).isEqualTo("김길동");
        System.out.println(fetchedArticle.getUser().getUsername());
    }

}