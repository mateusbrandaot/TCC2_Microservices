package com.agenday.registry.controller;

import com.agenday.registry.dto.Services.Provided.ServicesDTO;
import com.agenday.registry.service.ServicesProvidedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registry/services/")
public class ServicesProvidedController {

    @Autowired
    private ServicesProvidedService servicesProvidedService;

    @GetMapping("/provided/get/all")
    public ResponseEntity<ServicesDTO> getServicesWithInstitutionsAndEmployees() {
        ServicesDTO servicesDTO = servicesProvidedService.getServicesDTO();
        return ResponseEntity.ok(servicesDTO);
    }
}