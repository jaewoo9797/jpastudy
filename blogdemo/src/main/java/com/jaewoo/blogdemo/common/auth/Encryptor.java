package com.jaewoo.blogdemo.common.auth;

public interface Encryptor {
    String encrypt(String rawValue);
    boolean matches(String rawValue, String encryptedValue);
}
