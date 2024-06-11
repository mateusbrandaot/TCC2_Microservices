package com.agenday.registry.controller;

import com.agenday.registry.dto.StateDTO;
import com.agenday.registry.dto.StateResponseDTO;
import com.agenday.registry.popula.PopulateService;
import com.agenday.registry.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("registry/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private PopulateService populateService;

    @GetMapping("/get/all")
    public ResponseEntity<List<StateResponseDTO>> getAllStates() {
        return ResponseEntity.ok(stateService.findAllStates());
    }

    @GetMapping("popula/all")
    public ResponseEntity<String> populateDatabase() throws Exception{
        populateService.populateStatesAndCities();
        return ResponseEntity.ok("Dados de Estados e Cidades inseridos com sucesso!");
    }

}