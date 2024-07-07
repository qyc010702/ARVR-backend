package com.ist.datalog.business.controller;

import com.ist.datalog.business.model.Check;
import com.ist.datalog.business.model.Equipment;
import com.ist.datalog.business.repo.EquipmentRepo;
import com.ist.datalog.business.service.CheckService;
import com.ist.datalog.business.service.EquipmentService;
import com.ist.datalog.core.AbstractHttpDTO;
import com.ist.datalog.core.BodyDTO;
import com.ist.datalog.core.response.HttpResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @PostMapping("/create")
    public ResponseEntity<AbstractHttpDTO> createEquipment(@RequestBody Equipment equipment) {
        Equipment createEquipment = equipmentService.createEquipment(equipment);
        return HttpResponseEntity.of(BodyDTO.success(createEquipment));
    }

    @GetMapping("/allEquipments")
    public List<Equipment> getAllEquipments() {
        List<Equipment> equipments = equipmentService.getAllEquipment();
        return equipments;
    }

    @GetMapping("/find/{name}")
    public Equipment findEquipment(@PathVariable String name) {
        Equipment equipment = equipmentService.getEquipmentByName(name);
        return equipment;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbstractHttpDTO> updateEquipment(@PathVariable String id, @RequestBody Equipment equipment) {
        Equipment newEquipment = equipmentService.updateEquipment(id, equipment);
        return HttpResponseEntity.of(BodyDTO.success(newEquipment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AbstractHttpDTO> deleteEquipmentById (@PathVariable String id) {
        equipmentService.deleteEquipmentById(id);
        return HttpResponseEntity.of(BodyDTO.success());
    }

    @GetMapping("/getmodel/{id}")
    public ResponseEntity<AbstractHttpDTO> findEquipmentByName(@PathVariable String id) {
        Equipment equipment = equipmentService.getEquipmentById(id);

        return HttpResponseEntity.of(BodyDTO.success(equipment.getModel()));
    }
}
