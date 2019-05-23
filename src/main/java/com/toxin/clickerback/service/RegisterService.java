package com.toxin.clickerback.service;

import com.toxin.clickerback.entity.Register;
import com.toxin.clickerback.entity.User;
import com.toxin.clickerback.repository.RegisterRepository;
import com.toxin.clickerback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final RegisterRepository registerRepository;
    private final UserService userService;
    private final CounterService counterService;

    @Autowired
    public RegisterService(
        UserRepository userRepository,
        RegisterRepository registerRepository,
        UserService userService,
        CounterService counterService
    ) {
        this.userRepository = userRepository;
        this.registerRepository = registerRepository;
        this.userService = userService;
        this.counterService = counterService;
    }

    public void register(String from, String to) {
        Optional<User> optionalFrom = userRepository.findById(from);
        Optional<User> optionalTo = userRepository.findById(to);

        if (!optionalFrom.isPresent() || !optionalTo.isPresent()) return;

        User userFrom = optionalFrom.get();
        User userTo = optionalTo.get();

        registerRepository.save(
            new Register(userFrom, userTo)
        );

        if (check(userFrom, userTo))  {
            complete(userFrom, userTo);
        }
    }

    private void complete(User userFrom, User userTo) {
        counterService.generate(userFrom);
        counterService.generate(userTo);

        userService.complete(userFrom.getId());
        userService.complete(userTo.getId());
    }

    private boolean check(User first, User second) {
        Optional<Register> optionalFirst = registerRepository.findByFrom(first);
        Optional<Register> optionalSecond = registerRepository.findByFrom(second);

        if (!optionalFirst.isPresent() || !optionalSecond.isPresent()) return false;

        Register registerFirst = optionalFirst.get();
        Register registerSecond = optionalSecond.get();

        return registerFirst.getFrom().equals(registerSecond.getTo()) && registerSecond.getFrom().equals(registerFirst.getTo());
    }
}
