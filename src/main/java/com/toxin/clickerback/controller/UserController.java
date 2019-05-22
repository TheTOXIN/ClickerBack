package com.toxin.clickerback.controller;

import com.toxin.clickerback.api.ConnectAPI;
import com.toxin.clickerback.entity.User;
import com.toxin.clickerback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/create")
    public User create(@RequestBody String name) {
        return userService.create(name);
    }

    @PatchMapping("/connect")
    public void connect(@RequestBody ConnectAPI api) {
        userService.connect(api);
    }
}
