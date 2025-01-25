package com.jaewoo.blogdemo.user.controller;

import com.jaewoo.blogdemo.user.dto.RegisterUserRequest;
import com.jaewoo.blogdemo.user.service.ifs.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/new")
    public String newUser() {
        return "user/register";
    }

    @PostMapping("/registration")
    public String create(
            @Valid
            RegisterUserRequest request
    ) {
        userService.register(request);

        return "redirect:/";
    }
}
