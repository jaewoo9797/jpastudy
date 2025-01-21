package com.jaewoo.blogdemo.user.entity;

import com.jaewoo.blogdemo.common.baseentity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    // TODO 입력을 받을 때 컨트롤러 레이어에서 입력을 검증하는 validator 를 만들어 검증하자!
    @Column(nullable = false, length = 50)
    private String encryptedPassword;

    @Embedded
    private Email email;

    public static User create(String username, Email email, String encryptedPassword) {
        return User.builder()
                .username(username)
                .email(email)
                .encryptedPassword(encryptedPassword)
                .build();
    }
}

