package com.jaewoo.blogdemo.user.db;

import com.jaewoo.blogdemo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
