package com.toxin.clickerback.repository;

import com.toxin.clickerback.entity.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends MongoRepository<Counter, Long> {
}
