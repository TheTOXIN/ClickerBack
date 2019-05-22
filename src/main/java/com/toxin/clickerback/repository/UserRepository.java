package com.toxin.clickerback.repository;

import com.toxin.clickerback.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByToken(UUID token);

}
