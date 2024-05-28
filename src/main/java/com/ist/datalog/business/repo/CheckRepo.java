package com.ist.datalog.business.repo;

import com.ist.datalog.business.model.Check;
import com.ist.datalog.business.model.Model;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CheckRepo extends MongoRepository<Check, String> {
}
