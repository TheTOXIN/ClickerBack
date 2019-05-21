package com.toxin.clickerback.repository;

import com.toxin.clickerback.entity.Register;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends MongoRepository<Register, Long> {
}
