package com.toxin.clickerback.controller;

import com.toxin.clickerback.api.StateAPI;
import com.toxin.clickerback.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class CounterController {

    private final CounterService counterService;

    @Autowired
    public CounterController(
        CounterService counterService
    ) {
        this.counterService = counterService;
    }

    @MessageMapping("/click")
    public void click(String token) {
        this.counterService.click(UUID.fromString(token));
    }

    @ResponseBody
    @GetMapping(path = "/state/{userId}")
    public StateAPI state(@PathVariable("userId") String userId) {
        return counterService.state(userId);
    }
}
