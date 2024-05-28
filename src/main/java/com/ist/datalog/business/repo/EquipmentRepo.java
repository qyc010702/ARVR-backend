package com.ist.datalog.business.repo;

import com.ist.datalog.business.model.Check;
import com.ist.datalog.business.model.Equipment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EquipmentRepo extends MongoRepository<Equipment, String> {
}
