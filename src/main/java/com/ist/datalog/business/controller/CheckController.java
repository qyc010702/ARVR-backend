package com.ist.datalog.business.controller;

import com.ist.datalog.business.model.Check;
import com.ist.datalog.business.model.Model;
import com.ist.datalog.business.service.CheckService;
import com.ist.datalog.business.service.ModelService;
import com.ist.datalog.core.AbstractHttpDTO;
import com.ist.datalog.core.BodyDTO;
import com.ist.datalog.core.response.HttpResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/check")
public class CheckController {
    @Autowired
    private CheckService checkService;

    @PostMapping("/create")
    public ResponseEntity<AbstractHttpDTO> createCheck(@RequestBody Check check) {
        Check createCheck = checkService.createCheck(check);
        return HttpResponseEntity.of(BodyDTO.success(createCheck));
    }

    @GetMapping("/allChecks")
    public ResponseEntity<AbstractHttpDTO> getAllChecks() {
        List<Check> checks = checkService.getAllCheck();
        return HttpResponseEntity.of(BodyDTO.success(checks));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbstractHttpDTO> updateCheck(@PathVariable String id, @RequestBody Check check) {
        Check newCheck = checkService.updateCheck(id, check);
        return HttpResponseEntity.of(BodyDTO.success(newCheck));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AbstractHttpDTO> deleteCheckById (@PathVariable String id) {
        checkService.deleteCheckById(id);
        return HttpResponseEntity.of(BodyDTO.success());
    }
}
