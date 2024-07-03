package com.ist.datalog.business.service;

import com.ist.datalog.business.model.Equipment;
import com.ist.datalog.business.model.Model;
import com.ist.datalog.business.repo.EquipmentRepo;
import com.ist.datalog.business.repo.ModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ModelService {
    @Autowired
    ModelRepo modelRepo;

    @Autowired
    EquipmentRepo equipmentRepo;

    public List<Model> getAllModel(){return modelRepo.findAll();}

    public Model getModelById(String id){
        return modelRepo.findById(id).orElse(null);
    }

    public Model createModel(Model model){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        model.setTime(date.toString());
        return modelRepo.save(model);
    }

    public void deleteModelById(String id){
        modelRepo.deleteById(id);
    }

    public Model updateModel(String id , Model newModel){
        Model model = modelRepo.findById(id).orElse(null);
        if (model == null) {
            throw new RuntimeException("规则不存在");
        } else {
            Model changedModel = new Model();
            changedModel.setId(id);
            changedModel.setName(newModel.getName());
            Date date = new Date(System.currentTimeMillis());
            changedModel.setTime(date.toString());
            changedModel.setDirectory(newModel.getDirectory());
            changedModel.setDetail(newModel.getDetail());
            return modelRepo.save(changedModel);
        }
    }

    public List<Equipment> getEquipmentByModelName(String name) {
        Optional<Model> modelOptional = modelRepo.findByName(name);
        if (modelOptional.isPresent()) {
            String model = modelOptional.get().getName();
            return equipmentRepo.findByModel(model);
        } else {
            throw new RuntimeException("Model not found");
        }
    }
}
