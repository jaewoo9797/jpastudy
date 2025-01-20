package com.jaewoo.blogdemo.user.entity;

import jakarta.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {
    private static final String PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(PATTERN);
    private String email;

    private Email(String email) {
        this.email = email;
    }

    public static Email of(String email) {
        isValidOrThrow(email);
        return new Email(email);
    }

    private static void isValidOrThrow(String email) {
        Objects.requireNonNull(email, "email must not be null");
        if (!isValidEmail(email)) {
            String format = String.format("Invalid email address: %s", email);
            throw new IllegalArgumentException(format);
        }
    }

    private static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return email;
    }
}
