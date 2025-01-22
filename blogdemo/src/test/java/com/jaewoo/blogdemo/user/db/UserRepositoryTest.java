package com.jaewoo.blogdemo.user.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.jaewoo.blogdemo.common.auth.Encryptor;
import com.jaewoo.blogdemo.common.auth.PasswordEncryptor;
import com.jaewoo.blogdemo.common.config.JpaAuditingConfiguration;
import com.jaewoo.blogdemo.user.entity.Email;
import com.jaewoo.blogdemo.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({JpaAuditingConfiguration.class, PasswordEncryptor.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    private static final String username = "hong";
    private static final String password = "~Hong123";
    private static final String email = "hong@gmail.com";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Encryptor passwordEncryptor;

    private User user;

    @BeforeEach
    void setUp() {
        var encryptedPassword = passwordEncryptor.encrypt(password);
        user = User.create(username, Email.of(email), encryptedPassword);
    }

    @Test
    void 새로운_유저객체_데이터베이스_저장_저장된_객체의_정보는_동일해야한다() {
        // given
        // when
        User savedUser = userRepository.save(user);

        // then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    void 새로운_유저를_저장한_후_모든_유저조회_반환() {
        // given
        String username = "kimgildong";
        String password = "~Kingil123";
        String emailStr = "kim@qq.com";
        Email email = Email.of(emailStr);
        var user1 = User.create(username, email, password);

        userRepository.save(user);
        userRepository.save(user1);

        // when
        List<User> userList = userRepository.findAll();

        // then
        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(2);
    }

    @Test
    void 저장되어있는_유저의_정보를_id_값으로_조회한다() {
        // given
        userRepository.save(user);

        // when
        var foundUser = userRepository.findById(user.getId()).get();

        // then
        assertThat(foundUser).isNotNull();
    }

    @Test
    void 유저저장_후_유저의_정보를_수정_정상적으로_수정() {
        // given
        userRepository.save(user);
        // when
        var savedUser = userRepository.findById(user.getId()).get();
        savedUser.changeUsername("kingildong");
        var updatedUser = userRepository.save(savedUser);
        // then
        assertThat(updatedUser.getUsername()).isEqualTo("kingildong");
    }

    @Test
    void 유저를_삭제하면_조회하면_null반환() {
        // given
        userRepository.save(user);
        // when
        userRepository.deleteById(user.getId());
        Optional<User> userOptional = userRepository.findById(user.getId());
        // then
        assertThat(userOptional).isEmpty();
    }
}