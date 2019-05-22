package com.toxin.clickerback.controller;

import com.toxin.clickerback.api.RegisterAPI;
import com.toxin.clickerback.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @ResponseBody
    @PostMapping(path = "/register", produces = "application/json")
    public void register(@RequestBody RegisterAPI api) {
        registerService.register(api.getFrom(), api.getTo());
    }
}
