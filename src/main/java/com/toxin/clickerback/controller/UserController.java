package com.toxin.clickerback.controller;

import com.toxin.clickerback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @MessageMapping("/create")
    public void create(String name) {
        Long id = userService.create(name);
        System.out.println(id);
    }
}
