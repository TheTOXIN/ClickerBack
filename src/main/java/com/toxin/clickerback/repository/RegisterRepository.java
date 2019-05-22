package com.toxin.clickerback.repository;

import com.toxin.clickerback.entity.Register;
import com.toxin.clickerback.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegisterRepository extends MongoRepository<Register, String> {

    Optional<Register> findByFrom(User from);

}
