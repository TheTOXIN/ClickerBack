package com.toxin.clickerback.service;

import com.toxin.clickerback.api.ConnectAPI;
import com.toxin.clickerback.entity.User;
import com.toxin.clickerback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    private Map<String, String> connecter = new HashMap<>();

    private final SimpMessagingTemplate template;
    private final UserRepository userRepository;

    @Autowired
    public UserService(
        UserRepository userRepository,
        SimpMessagingTemplate template
    ) {
        this.userRepository = userRepository;
        this.template = template;
    }

    public User create(String name) {
        return userRepository.save(new User(
            name,
            UUID.randomUUID()
        ));
    }

    public void connect(ConnectAPI api) {
        connecter.put(api.getUserId(), api.getSocketId());
    }

    public void complete(String userId) {
        send(connecter.get(userId), "connect");

    }

    public void click(String userId) {
        send(connecter.get(userId), "clicker");
    }

    public void send(String socketId, String queueId) {
        if(socketId == null) return;

        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);

        headerAccessor.setSessionId(socketId);
        headerAccessor.setLeaveMutable(true);

        template.convertAndSendToUser(socketId, "/queue/" + queueId, "", headerAccessor.getMessageHeaders());
    }
}
