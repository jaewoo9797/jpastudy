package com.jaewoo.blogdemo.comment.db;

import com.jaewoo.blogdemo.comment.entity.Comment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByArticleIdAndWriterId(Long article_id, Long writer_id);
}
