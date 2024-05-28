package com.ist.datalog.business.service;

import com.ist.datalog.business.model.Check;
import com.ist.datalog.business.model.Model;
import com.ist.datalog.business.repo.CheckRepo;
import com.ist.datalog.business.repo.ModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CheckService {
    @Autowired
    CheckRepo checkRepo;
    public List<Check> getAllCheck(){return checkRepo.findAll();}

    public Check getCheckById(String id){
        return checkRepo.findById(id).orElse(null);
    }

    public Check createCheck(Check check){
        Date date = new Date(System.currentTimeMillis());
        check.setTime(date.toString());
        return checkRepo.save(check);
    }

    public void deleteCheckById(String id){
        checkRepo.deleteById(id);
    }

    public Check updateCheck(String id , Check newCheck){
        Check model = checkRepo.findById(id).orElse(null);
        if (model == null) {
            throw new RuntimeException("规则不存在");
        } else {
            Check changedCheck = new Check();
            changedCheck.setId(id);
            changedCheck.setPlace(newCheck.getPlace());
            Date date = new Date(System.currentTimeMillis());
            changedCheck.setTime(date.toString());
            changedCheck.setPerson(newCheck.getPerson());
            changedCheck.setStatus(newCheck.getStatus());
            return checkRepo.save(changedCheck);
        }
    }
}
