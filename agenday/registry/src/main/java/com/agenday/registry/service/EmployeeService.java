package com.agenday.registry.service;

import com.agenday.registry.dto.EmployeeDTO;
import com.agenday.registry.dto.SpecialtyDTO;
import com.agenday.registry.exception.ForbiddenException;
import com.agenday.registry.model.Employee;
import com.agenday.registry.model.Institution;
import com.agenday.registry.model.Specialty;
import com.agenday.registry.repository.EmployeeRepository;
import com.agenday.registry.repository.InstitutionRepository;
import com.agenday.registry.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        try {
            Institution institution = institutionRepository.findById(employeeDTO.getInstitutionId()).orElseThrow();
            List<Specialty> specialties = specialtyRepository.findAllById(employeeDTO.getSpecialtyIds());

            Employee employee = new Employee();
            employee.setId(employeeDTO.getId());
            employee.setName(employeeDTO.getName());
            employee.setCpf(employeeDTO.getCpf());
            employee.setEmail(employeeDTO.getEmail());
            employee.setInstitution(institution);
            employee.setSpecialties(specialties);

            Employee savedEmployee = employeeRepository.save(employee);

            List<SpecialtyDTO> specialtyDTOs = specialties.stream().map(specialty -> new SpecialtyDTO(
                    specialty.getId(),
                    specialty.getName(),
                    specialty.getDescription()
            )).collect(Collectors.toList());

            return new EmployeeDTO(
                    savedEmployee.getId(),
                    savedEmployee.getName(),
                    savedEmployee.getCpf(),
                    savedEmployee.getEmail(),
                    savedEmployee.getInstitution().getId(),
                    null, // No need to set specialtyIds for the response
                    specialtyDTOs

            );
        } catch (Exception e) {
            throw new ForbiddenException("Message: " + e.getMessage());
        }
    }

    public List<EmployeeDTO> findEmployeesByIdInstitution(Long institutionId) {
        try {
            List<Employee> employees = employeeRepository.findByInstitutionId(institutionId);
            return employees.stream().map(employee -> new EmployeeDTO(
                    employee.getId(),
                    employee.getName(),
                    employee.getCpf(),
                    employee.getEmail(),
                    employee.getInstitution().getId(),
                    null, // No need to set specialtyIds for the response
                    employee.getSpecialties().stream().map(specialty -> new SpecialtyDTO(
                            specialty.getId(),
                            specialty.getName(),
                            specialty.getDescription()
                    )).collect(Collectors.toList())

            )).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ForbiddenException("Message: " + e.getMessage());
        }
    }

    public void deleteEmployee(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            throw new ForbiddenException("Message: " + e.getMessage());
        }
    }
}
