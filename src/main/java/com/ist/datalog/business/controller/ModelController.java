package com.ist.datalog.business.controller;

import com.ist.datalog.business.model.Equipment;
import com.ist.datalog.business.model.Model;
import com.ist.datalog.business.service.ModelService;
import com.ist.datalog.core.AbstractHttpDTO;
import com.ist.datalog.core.BodyDTO;
import com.ist.datalog.core.response.HttpResponseEntity;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @PostMapping("/create")
    public ResponseEntity<AbstractHttpDTO> createModel(@RequestParam String name,
                                                       @RequestParam String directory,
                                                       @RequestParam String detail,
                                                       @RequestParam MultipartFile image) throws IOException {

        Model newModel = new Model();
        newModel.setName(name);
        newModel.setDirectory(directory);
        newModel.setDetail(detail);
        newModel.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        newModel.setTime(new java.util.Date().toString());
        Model createModel = modelService.createModel(newModel);
        return HttpResponseEntity.of(BodyDTO.success(createModel));
    }

    @GetMapping("/allModels")
    public ResponseEntity<AbstractHttpDTO> getAllModels() {
        List<Model> models = modelService.getAllModel();
        return HttpResponseEntity.of(BodyDTO.success(models));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbstractHttpDTO> updateModel(@PathVariable String id,
                                                       @RequestParam String name,
                                                       @RequestParam String directory,
                                                       @RequestParam String detail,
                                                       @RequestParam(required = false) MultipartFile image)throws IOException {
        Model newModel = new Model();
        newModel.setName(name);
        newModel.setDirectory(directory);
        newModel.setDetail(detail);
        if (image != null && !image.isEmpty()) {
            newModel.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        }
        newModel.setTime(new java.util.Date().toString());
        modelService.updateModel(id, newModel);
        return HttpResponseEntity.of(BodyDTO.success(newModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AbstractHttpDTO> deleteModelById (@PathVariable String id) {
        modelService.deleteModelById(id);
        return HttpResponseEntity.of(BodyDTO.success());
    }

    @GetMapping("/equipmentByModel")
    public ResponseEntity<AbstractHttpDTO> getEquipmentByModel(@RequestParam String name) {
        List<Equipment> equipments = modelService.getEquipmentByModelName(name);
        return HttpResponseEntity.of(BodyDTO.success(equipments));
    }

}
