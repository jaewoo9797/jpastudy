package com.jaewoo.blogdemo.article.service;

import static com.jaewoo.blogdemo.article.entity.enums.ArticleStatus.REGISTERED;

import com.jaewoo.blogdemo.article.db.ArticleRepository;
import com.jaewoo.blogdemo.article.dto.UpdateArticleRequest;
import com.jaewoo.blogdemo.article.dto.WriteArticleRequest;
import com.jaewoo.blogdemo.article.entity.Article;
import com.jaewoo.blogdemo.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public void writeArticle(User user, WriteArticleRequest request) {
        Article createdArticle = Article.create(request.title(), request.content(), user);
        articleRepository.save(createdArticle);
    }

    @Override
    public Article findArticleById(Long articleId) {
        // TODO 댓글도 함께 조회할텐데, 쿼리 최적화 필요함, DTO 만들기
        return articleRepository.findByIdAndStatus(articleId, REGISTERED)
                .orElseThrow();
    }

    @Override
    public List<Article> findAllArticle(Pageable pageable) {
        // TODO 글의 목록들을 페이징 처리하는데, 필요없는건 때도 괜찮음, DTO 만들기
        return articleRepository.findAllByStatus(REGISTERED, pageable);
    }

    @Override
    @Transactional
    public void updateArticle(User user, UpdateArticleRequest request) {
        Article foundArticle = articleRepository.findByIdAndUserId(request.articleId(), user.getId())
                .filter(Article::isRegistered)
                .orElseThrow();
        foundArticle.updateArticle(request.updateTitle(), request.updateContent());
    }

    @Override
    @Transactional
    public void deleteArticle(User user, Long articleId) {
        Article foundArticle = articleRepository.findByIdAndUserId(articleId, user.getId())
                .orElseThrow();
        foundArticle.unregister();
    }
}
