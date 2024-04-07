package com.pw.hatbox.controller;


import com.pw.hatbox.domain.entity.User;
import com.pw.hatbox.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public Flux<User> get() {
        return userService.get();
    }

    @GetMapping("/list")
    public Mono<List<User>> getList() {
        return userService.get().collectList();
    }

    @PostMapping
    public void create(@RequestBody User user) {
        userService.create(user);
    }
}
