package com.toxin.clickerback.service;

import com.toxin.clickerback.api.StateAPI;
import com.toxin.clickerback.entity.Counter;
import com.toxin.clickerback.entity.Register;
import com.toxin.clickerback.entity.User;
import com.toxin.clickerback.repository.CounterRepository;
import com.toxin.clickerback.repository.RegisterRepository;
import com.toxin.clickerback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class CounterService {

    private Map<UUID, String> session = new HashMap<>();

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
        userService.click(session.get(token));

        Optional<User> optionalUser = userRepository.findByToken(token);
        if (!optionalUser.isPresent()) return;

        Optional<Counter> optionalCounter = counterRepository.findByUser(optionalUser.get());
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

    public StateAPI state(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) return null;

        Optional<Register> optionalRegister = registerRepository.findByFrom(optionalUser.get());
        if (!optionalRegister.isPresent()) return null;

        User myUser = optionalUser.get();
        User meUser = optionalRegister.get().getTo();

        Optional<Counter> meCounterOptional = counterRepository.findByUser(meUser);
        Optional<Counter> myCounterOptional = counterRepository.findByUser(myUser);

        if (!meCounterOptional.isPresent() || !myCounterOptional.isPresent()) return null;

        int meCount = meCounterOptional.get().getCount();
        int myCount = myCounterOptional.get().getCount();

        session.put(myUser.getToken(), meUser.getId());

        return new StateAPI(meUser.getName(), meCount, myCount);
    }
}
