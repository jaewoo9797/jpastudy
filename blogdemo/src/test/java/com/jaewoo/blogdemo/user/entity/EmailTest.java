package com.jaewoo.blogdemo.user.entity;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EmailTest {

    @Test
    void 요구사항에_맞는_이메일_객체생성() {
        // given
        String emailStr = "hong@mail.com";
        // when
        Email email = Email.of(emailStr);
        // then
        assertThat(email.getEmail()).isEqualTo(emailStr);
    }

    @Test
    void 유효하지않는_이메일_객체생성_에러던짐() {
        // given
        String emailStr = "ab@aba";
        // when
        Throwable error = catchThrowable(() -> Email.of(emailStr));
        // then
        assertThat(error).isInstanceOf(IllegalArgumentException.class);
    }
}