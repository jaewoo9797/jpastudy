package com.jaewoo.blogdemo.common.auth.controller;

import com.jaewoo.blogdemo.user.dto.RegisterUserRequest;
import com.jaewoo.blogdemo.user.service.ifs.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model mode) {

        return "/user/register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterUserRequest request) {
        log.info("register user: {}", request);
        userService.register(request);
        return "redirect:/";
    }
}
