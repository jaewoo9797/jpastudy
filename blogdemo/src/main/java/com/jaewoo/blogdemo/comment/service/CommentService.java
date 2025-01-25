package com.jaewoo.blogdemo.comment.service;

import com.jaewoo.blogdemo.comment.dto.CreateCommentRequest;
import com.jaewoo.blogdemo.comment.dto.UpdateCommentRequest;
import com.jaewoo.blogdemo.user.entity.User;

public interface CommentService {

    // 댓글 생성
    void addComment(User writer, CreateCommentRequest request);

    // 댓글 수정
    void updateComment(User writer, UpdateCommentRequest request);

    // 댓글 삭제
    void deleteCommentById(Long commentId);
}
