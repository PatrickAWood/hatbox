package com.pw.hatbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/")
public class BaseController {

    @GetMapping
    public String get() {
        return """
            Hello and welcome to hatbox: <a href="/user">reactive stream of users</a>; <a href="/user/list">list of users</a>
        """;
    }

}
