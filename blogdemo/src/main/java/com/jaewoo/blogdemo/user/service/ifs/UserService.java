package com.jaewoo.blogdemo.user.service.ifs;

import com.jaewoo.blogdemo.article.entity.Article;
import com.jaewoo.blogdemo.user.dto.ChangePasswordRequest;
import com.jaewoo.blogdemo.user.dto.CheckPasswordRequest;
import com.jaewoo.blogdemo.user.dto.RegisterUserRequest;
import com.jaewoo.blogdemo.user.entity.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void register(RegisterUserRequest registerUserRequest);

    void changePassword(User user, ChangePasswordRequest request);

    void unregister(User user);

    void matchUserPassword(User user, CheckPasswordRequest request);

    // 유저가 작성한 article 조회 - 페이징 조건을 어떻게 줄까? pageable 이용하자.
    List<Article> findUserWritingArticleWithPagination(User user, Pageable pageable);

    // 유저가 작성한 댓글 - 페이징, article 제목도 알아야 하지 않을까?
}
