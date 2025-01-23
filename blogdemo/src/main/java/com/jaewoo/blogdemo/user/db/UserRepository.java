package com.jaewoo.blogdemo.user.db;

import com.jaewoo.blogdemo.article.entity.Article;
import com.jaewoo.blogdemo.user.entity.Email;
import com.jaewoo.blogdemo.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(Email email);


}
