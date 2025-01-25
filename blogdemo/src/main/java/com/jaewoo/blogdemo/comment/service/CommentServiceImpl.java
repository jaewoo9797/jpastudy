package com.jaewoo.blogdemo.comment.service;

import com.jaewoo.blogdemo.article.db.ArticleRepository;
import com.jaewoo.blogdemo.article.entity.Article;
import com.jaewoo.blogdemo.article.entity.enums.ArticleStatus;
import com.jaewoo.blogdemo.comment.db.CommentRepository;
import com.jaewoo.blogdemo.comment.dto.CreateCommentRequest;
import com.jaewoo.blogdemo.comment.dto.UpdateCommentRequest;
import com.jaewoo.blogdemo.comment.entity.Comment;
import com.jaewoo.blogdemo.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @Override
    @Transactional
    public void addComment(User writer, CreateCommentRequest request) {
        Article article = articleRepository.findByIdAndStatus(request.articleId(), ArticleStatus.REGISTERED)
                .orElseThrow();

        Comment comment = Comment.create(request.comment(), writer, article);
        article.addComment(comment);
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void updateComment(User writer, UpdateCommentRequest request) {
        Comment comment = commentRepository.findByArticleIdAndWriterId(request.articleId(), writer.getId())
                .orElseThrow();
        comment.updateComment(request.updateComment());
    }

    @Override
    @Transactional
    public void deleteCommentById(Long commentId) {
        commentRepository.findById(commentId)
                .ifPresent(Comment::unregister);
    }
}
