package com.jaewoo.blogdemo.user.service;

import com.jaewoo.blogdemo.article.db.ArticleRepository;
import com.jaewoo.blogdemo.article.entity.Article;
import com.jaewoo.blogdemo.user.db.UserRepository;
import com.jaewoo.blogdemo.user.dto.ChangePasswordRequest;
import com.jaewoo.blogdemo.user.dto.CheckPasswordRequest;
import com.jaewoo.blogdemo.user.dto.RegisterUserRequest;
import com.jaewoo.blogdemo.user.entity.Email;
import com.jaewoo.blogdemo.user.entity.User;
import com.jaewoo.blogdemo.user.service.ifs.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterUserRequest request) {

        User registerUser =
                toEntity(request.username(), request.password(), request.email());

        userRepository.save(registerUser);
    }

    @Override
    public void changePassword(User user, ChangePasswordRequest request) {
        user.changePassword(passwordEncoder.encode(request.changePassword()));
        userRepository.save(user);
    }

    /**
     * 1. 유저의 비밀번호를 재입력 받기 2. 회원탈퇴하는 이유 제공받을 수 있음 3. 소프트 삭제로 변경 가능
     */
    @Override
    public void unregister(User user) {
        user.unregister();
    }

    /**
     * 유저가 자신의 정보를 수정하기 위해서 거쳐야하는 비밀번호 체크 1. 유저가 비밀번호 체크를 했는지 안했는지 어떻게 검증할건지? 2. 이후 변경 시 비밀번호, 유저이름 등 한번에 동적으로 수정하려고할 때
     * 쿼리 만들어야겠네
     */
    @Override
    public void matchUserPassword(User user, CheckPasswordRequest request) {
        boolean matchesResult = passwordEncoder.matches(request.password(), user.getEncryptedPassword());
        if (!matchesResult) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }
    }

    @Override
    public List<Article> findUserWritingArticleWithPagination(User user, Pageable pageable) {
        Page<Long> articleIdsByUserId = articleRepository.findArticleIdsByUserId(user.getId(), pageable);
        return articleRepository.findArticlesWithDetailsByIds(articleIdsByUserId.getContent());
    }

    private User toEntity(String username, String password, String email) {
        Email inputEmail = Email.of(email);
        String encryptedPassword = passwordEncoder.encode(password);
        return User.create(username, inputEmail, encryptedPassword);
    }
}
