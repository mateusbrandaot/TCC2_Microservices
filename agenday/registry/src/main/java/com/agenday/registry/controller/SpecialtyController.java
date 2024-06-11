package com.agenday.registry.controller;

import com.agenday.registry.dto.SpecialtyDTO;
import com.agenday.registry.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.agenday.registry.model.Specialty;
import com.agenday.registry.service.SpecialtyService;

import java.util.List;

@RestController
@RequestMapping(value = "registry/specialties")
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;

    @GetMapping("institution/{institutionId}")
    public ResponseEntity<List<Specialty>> getSpecialtyInstitutionId(@PathVariable Long institutionId) {
        specialtyService.findAddressesByIdInstitution(institutionId);
        return ResponseEntity.ok(specialtyService.findAddressesByIdInstitution(institutionId));
    }
    @PostMapping("institution/{institutionId}")
    public ResponseEntity<String> createSpecialty(@RequestBody SpecialtyDTO specialty, @PathVariable Long institutionId) {
        specialtyService.saveSpecialty(specialty, institutionId);
        return ResponseEntity.ok("Specialty created successfully");
    }

    @PutMapping("specialty/{specialtyId}")
    public ResponseEntity<Specialty> updateSpecialty(@PathVariable Long id, @RequestBody SpecialtyDTO specialty) {
        return ResponseEntity.ok(specialtyService.saveSpecialtyUpdate(specialty, id));
    }

    @DeleteMapping("specialty/{specialtyId}")
    public ResponseEntity<Void> deleteSpecialty(@PathVariable Long id) {
        specialtyService.deleteSpecialty(id);
        return ResponseEntity.ok().build();
    }
}
