package com.jaewoo.blogdemo.user.entity;

import com.jaewoo.blogdemo.common.baseentity.BaseEntity;
import com.jaewoo.blogdemo.user.entity.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    // TODO 입력을 받을 때 컨트롤러 레이어에서 입력을 검증하는 validator 를 만들어 검증하자!
    @Column(nullable = false, length = 100, name = "password")
    private String encryptedPassword;

    @Embedded
    private Email email;

    public static User create(String username, Email email, String encryptedPassword) {
        return User.builder()
                .username(username)
                .status(UserStatus.REGISTERED)
                .email(email)
                .encryptedPassword(encryptedPassword)
                .build();
    }

    // 유저의 이름을 변경
    public void changeUsername(String username) {
        /**
         *  TODO
         *  이름을 검증하는 방법에 대해서 생각해보기
         *  - not null
         *  - size
         *  검증로직에 대해서 고민해보기!
         *  - 매개변수에 hibernate.validator 검증을 한다.
         *  - 클래스 내부에 검증 로직을 만들어 재사용한다.
         */
        this.username = username;
    }

    // 유저의 비밀번호를 변경
    public void changePassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    // 유저의 이메일은 필수, 유니크 한 값.
}

