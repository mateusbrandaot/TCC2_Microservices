package com.agenday.registry.controller;

import com.agenday.registry.model.ServiceType;
import com.agenday.registry.popula.PopulateService;
import com.agenday.registry.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("registry/service-types")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Autowired
    private PopulateService populateService;

    @GetMapping("/get/all")
    public ResponseEntity<List<ServiceType>> getAllServiceTypes() {
        return ResponseEntity.ok(serviceTypeService.findAllServiceTypes());
    }

    @GetMapping("popula/all")
    public ResponseEntity<String> populateDatabase() throws Exception{
        populateService.populateServiceTypes();
        return ResponseEntity.ok("Dados de Estados e Cidades inseridos com sucesso!");
    }

}