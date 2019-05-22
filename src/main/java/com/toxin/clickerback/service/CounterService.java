package com.toxin.clickerback.service;

import com.toxin.clickerback.entity.Counter;
import com.toxin.clickerback.entity.Register;
import com.toxin.clickerback.entity.User;
import com.toxin.clickerback.repository.CounterRepository;
import com.toxin.clickerback.repository.RegisterRepository;
import com.toxin.clickerback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CounterService {

    private final CounterRepository counterRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final RegisterRepository registerRepository;

    @Autowired
    public CounterService(
        CounterRepository counterRepository,
        UserService userService,
        UserRepository userRepository,
        RegisterRepository registerRepository
    ) {
        this.counterRepository = counterRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.registerRepository = registerRepository;
    }

    public void click(UUID token) {
        Optional<User> optionalUser = userRepository.findByToken(token);
        if (!optionalUser.isPresent()) return;

        User user = optionalUser.get();

        Optional<Register> registerOptional = registerRepository.findByFrom(user);
        if (!registerOptional.isPresent()) return;

        userService.click(registerOptional.get().getFrom().getId());

        Optional<Counter> optionalCounter = counterRepository.findByUser(user);
        if (!optionalCounter.isPresent()) return;

        Counter counter = optionalCounter.get();
        counter.setCount(counter.getCount() + 1);

        counterRepository.save(counter);
    }

    public void generate(User user) {
        Optional<Counter> optionalCounter = counterRepository.findByUser(user);

        Counter counter;

        if (optionalCounter.isPresent()) {
            counter = optionalCounter.get();
            counter.setCount(0);
        } else {
            counter = new Counter();
            counter.setUser(user);
        }

        counterRepository.save(counter);
    }
}
