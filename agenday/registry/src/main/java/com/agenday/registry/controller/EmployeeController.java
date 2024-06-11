package com.agenday.registry.controller;

import com.agenday.registry.dto.EmployeeDTO;
import com.agenday.registry.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("registry/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("institution/{institutionId}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesInstitutionId(@PathVariable Long institutionId) {
        return ResponseEntity.ok(employeeService.findEmployeesByIdInstitution(institutionId));
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.saveEmployee(employeeDTO));
    }

    @PutMapping("employee/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        employeeDTO.setId(id);
        return ResponseEntity.ok(employeeService.saveEmployee(employeeDTO));
    }

    @DeleteMapping("employee/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
