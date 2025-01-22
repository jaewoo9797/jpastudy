package com.jaewoo.blogdemo.comment.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.jaewoo.blogdemo.article.db.ArticleRepository;
import com.jaewoo.blogdemo.article.entity.Article;
import com.jaewoo.blogdemo.comment.entity.Comment;
import com.jaewoo.blogdemo.common.config.JpaAuditingConfiguration;
import com.jaewoo.blogdemo.user.db.UserRepository;
import com.jaewoo.blogdemo.user.entity.Email;
import com.jaewoo.blogdemo.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(value = {JpaAuditingConfiguration.class})
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    private User user = User.create("홍길동", Email.of("hong@mail.com"), "123123");
    private Article article = Article.create("test subject", "test content abcedf", user);
    private Comment comment;

    @BeforeEach
    void setUp() {
        userRepository.save(user);
        articleRepository.save(article);
        comment = Comment.create("댓글 테스트", user, article);
    }

    @Test
    void 댓글생성_저장() {
        // given
        // when
        var savedComment = articleRepository.save(article);
        // then
        assertThat(savedComment).isNotNull();
        assertThat(savedComment.getId()).isGreaterThan(0);
    }

    @Test
    void 새로운_댓글_저장후_모든_댓글_조회() {
        // given
        articleRepository.save(article);
        // when
        List<Article> articles = articleRepository.findAll();
        // then
        assertThat(articles).isNotEmpty();
        assertThat(articles.size()).isEqualTo(1);
    }

    @Test
    void 저장되어있는_댓글_id_조회() {
        //given
        commentRepository.save(comment);
        // when
        Optional<Comment> foundComment = commentRepository.findById(comment.getId());
        // then
        assertThat(foundComment).isNotEmpty();
    }

    @Test
    void 댓글_수정_정상적으로_수정됨() {
        //given
        var savedComment = commentRepository.save(comment);
        String changeContent = "change test";
        // when
        savedComment.changeContent(changeContent);
        Comment updatedComment = commentRepository.save(savedComment);
        // then
        assertThat(updatedComment.getContent()).isEqualTo(changeContent);
    }

    @Test
    void 댓글_삭제_조회시_빈Optional반환() {
        //given
        commentRepository.save(comment);
        // when
        commentRepository.deleteById(comment.getId());
        Optional<Comment> deletedComment = commentRepository.findById(comment.getId());
        // then
        assertThat(deletedComment).isEmpty();
    }
}