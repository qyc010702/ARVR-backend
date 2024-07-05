package com.ist.datalog.business.repo;

import com.ist.datalog.business.model.Check;
import com.ist.datalog.business.model.Equipment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepo extends MongoRepository<Equipment, String> {

    List<Equipment> findByModel(String model);

}
