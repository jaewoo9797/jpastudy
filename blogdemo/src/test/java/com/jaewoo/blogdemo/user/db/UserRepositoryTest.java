package com.jaewoo.blogdemo.user.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.jaewoo.blogdemo.common.auth.Encryptor;
import com.jaewoo.blogdemo.common.auth.PasswordEncryptor;
import com.jaewoo.blogdemo.common.config.JpaConfig;
import com.jaewoo.blogdemo.user.entity.Email;
import com.jaewoo.blogdemo.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({JpaConfig.class, PasswordEncryptor.class})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Encryptor passwordEncryptor;

    @Test
    void 새로운_유저객체_데이터베이스_저장된_객체_조회시_동일해야한다() {
        // given
        String username = "hong";
        String password = "test";
        String emailStr = "hong@qq.com";
        Email email = Email.of(emailStr);
        // when
        passwordEncryptor.encrypt(password);
        var createdUser = User.create(username, email, password);

        User savedUser = userRepository.save(createdUser);
        // then
        assertAll(
                () -> {
                    assertThat(savedUser).isNotNull();
                    assertAll(
                            () -> assertThat(savedUser.getUsername()).isEqualTo(username),
                            () -> assertThat(savedUser.getEmail()).isEqualTo(email)
                    );
                }
        );
    }
}