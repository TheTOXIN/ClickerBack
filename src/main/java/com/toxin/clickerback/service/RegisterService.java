package com.toxin.clickerback.service;

import com.toxin.clickerback.entity.Register;
import com.toxin.clickerback.entity.User;
import com.toxin.clickerback.repository.RegisterRepository;
import com.toxin.clickerback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

        Register register = registerRepository
            .findByFrom(userFrom)
            .orElse(new Register(userFrom, userTo));

        register.setTo(userTo);

        counterService.generate(userFrom);
        registerRepository.save(register);

        checkRegister(userFrom, userTo);
    }

    private void checkRegister(User first, User second) {
        Optional<Register> optionalFirst = registerRepository.findByFrom(first);
        Optional<Register> optionalSecond = registerRepository.findByFrom(second);

        if (!optionalFirst.isPresent() || !optionalSecond.isPresent()) return;

        Register registerFirst = optionalFirst.get();
        Register registerSecond = optionalSecond.get();

        if (registerFirst.getFrom().equals(registerSecond.getTo()) && registerSecond.getFrom().equals(registerFirst.getTo())) {
            userService.complete(first.getId());
            userService.complete(second.getId());
        }
    }
}
