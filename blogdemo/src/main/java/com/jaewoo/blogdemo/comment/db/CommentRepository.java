package com.jaewoo.blogdemo.comment.db;

import com.jaewoo.blogdemo.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
