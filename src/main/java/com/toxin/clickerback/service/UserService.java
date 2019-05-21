package com.toxin.clickerback.service;

import com.toxin.clickerback.entity.User;
import com.toxin.clickerback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long create(String name) {
        User user = new User();

        user.setName(name);
        user.setToken(UUID.randomUUID());
        user.setId(userRepository.count());

        userRepository.save(user);

        return user.getId();
    }
}
