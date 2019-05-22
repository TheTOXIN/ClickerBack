package com.toxin.clickerback.controller;

import com.toxin.clickerback.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

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
    public void click(UUID token) {
        this.counterService.click(token);
    }
}
