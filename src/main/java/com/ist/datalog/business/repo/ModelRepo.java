package com.ist.datalog.business.repo;

import com.ist.datalog.business.model.Model;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ModelRepo extends MongoRepository<Model, String> {
    Optional<Model> findByName(String name);

}
