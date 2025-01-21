package com.jaewoo.blogdemo.common.auth;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptor implements Encryptor {

    @Override
    public String encrypt(String rawValue) {
        return BCrypt.hashpw(rawValue, BCrypt.gensalt());
    }

    @Override
    public boolean matches(String rawValue, String encryptedValue) {
        return BCrypt.checkpw(rawValue, encryptedValue);
    }
}
