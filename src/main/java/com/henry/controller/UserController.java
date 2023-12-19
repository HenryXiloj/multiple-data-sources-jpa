package com.henry.controller;

import com.henry.model.user.User;
import com.henry.service.DefaultService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final DefaultService<User,Long> defaultService;

    public UserController(DefaultService<User, Long> defaultService) {
        this.defaultService = defaultService;
    }

    @PostMapping("/users")
    public User createEmployee(@RequestBody User user) {
        return defaultService.save(user);
    }
}
