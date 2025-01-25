package com.jaewoo.blogdemo.common.auth;

import com.jaewoo.blogdemo.user.entity.User;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetail implements UserDetails {

    private User user;

    public static UserDetail of(User user) {
        return new UserDetail(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return user.getEncryptedPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail().toString();
    }

}
