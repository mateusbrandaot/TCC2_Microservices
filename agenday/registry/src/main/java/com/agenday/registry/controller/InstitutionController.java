package com.agenday.registry.controller;

import com.agenday.registry.dto.InstitutionDTO;
import com.agenday.registry.model.Institution;
import com.agenday.registry.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registry/institution/")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @GetMapping("get/all/by/user/{userId}")
    public ResponseEntity<List<InstitutionDTO>> getAllInstitutions(@PathVariable Long userId) {
        return ResponseEntity.ok(institutionService.findAllInstitutionsByIdUser(userId));
    }

    @PostMapping("create/by/user/{userId}")
    public ResponseEntity<String> createInstitution(@RequestBody InstitutionDTO institution, @PathVariable Long userId) {
        String response = institutionService.saveInstitution(institution, userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update/institution/{institutionId}")
    public ResponseEntity<String> updateInstitution(@PathVariable Long institutionId, @RequestBody InstitutionDTO institution) {
        institutionService.saveInstitutionUpdate(institution, institutionId);
        return ResponseEntity.ok("Institution updated successfully");

    }
    @DeleteMapping("delete/{institutionId}")
    public ResponseEntity<Void> deleteInstitution(@PathVariable Long institutionId) {
        institutionService.deleteInstitution(institutionId);
        return ResponseEntity.ok().build();
    }
}