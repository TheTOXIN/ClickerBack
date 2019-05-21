package com.toxin.clickerback.service;

import com.toxin.clickerback.entity.User;
import com.toxin.clickerback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long create(User user) {
        return userRepository.save(user).getId();
    }
}
