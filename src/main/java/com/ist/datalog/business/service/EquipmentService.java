package com.ist.datalog.business.service;

import com.ist.datalog.business.model.Check;
import com.ist.datalog.business.model.Equipment;
import com.ist.datalog.business.repo.CheckRepo;
import com.ist.datalog.business.repo.EquipmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class EquipmentService {
    @Autowired
    EquipmentRepo equipmentRepo;
    public List<Equipment> getAllEquipment(){return equipmentRepo.findAll();}

    public Equipment getEquipmentByName(String id){
        return equipmentRepo.findByName(id);
    }

    public Equipment createEquipment(Equipment equipment){
        Date date = new Date(System.currentTimeMillis());
        equipment.setStatus("已检查");
        equipment.setInTime(date.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date newDate = calendar.getTime();
        equipment.setNextTime(newDate.toString());
        return equipmentRepo.save(equipment);
    }

    public void deleteEquipmentById(String id){
        equipmentRepo.deleteById(id);
    }

    public Equipment updateEquipment(String id , Equipment newEquipment){
        Equipment equipment = equipmentRepo.findById(id).orElse(null);
        if (equipment == null) {
            throw new RuntimeException("设备不存在");
        } else {
            Equipment changedEquipment = new Equipment();
            changedEquipment.setId(id);
            changedEquipment.setModel(newEquipment.getModel());
            Date date = new Date(System.currentTimeMillis());
            changedEquipment.setInTime(date.toString());
            changedEquipment.setName(newEquipment.getName());
            changedEquipment.setStatus(newEquipment.getStatus());
            return equipmentRepo.save(changedEquipment);
        }
    }


    public Equipment getEquipmentById(String id) {
        return equipmentRepo.findByName(id);
    }
}
