package com.ist.datalog.business.controller;

import com.ist.datalog.business.model.Model;
import com.ist.datalog.business.service.ModelService;
import com.ist.datalog.core.AbstractHttpDTO;
import com.ist.datalog.core.BodyDTO;
import com.ist.datalog.core.response.HttpResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @PostMapping("/create")
    public ResponseEntity<AbstractHttpDTO> createModel(@RequestBody Model model) {
        Model createModel = modelService.createModel(model);
        System.out.println("111");
        return HttpResponseEntity.of(BodyDTO.success(createModel));
    }

    @GetMapping("/allModels")
    public ResponseEntity<AbstractHttpDTO> getAllModels() {
        List<Model> models = modelService.getAllModel();
        return HttpResponseEntity.of(BodyDTO.success(models));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbstractHttpDTO> updateModel(@PathVariable String id, @RequestBody Model model) {
        Model newModel = modelService.updateModel(id, model);
        return HttpResponseEntity.of(BodyDTO.success(newModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AbstractHttpDTO> deleteModelById (@PathVariable String id) {
        modelService.deleteModelById(id);
        return HttpResponseEntity.of(BodyDTO.success());
    }
}
