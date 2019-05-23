package com.toxin.clickerback.repository;

import com.toxin.clickerback.entity.Counter;
import com.toxin.clickerback.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounterRepository extends MongoRepository<Counter, String> {

    Optional<Counter> findByUser(User user);

}
