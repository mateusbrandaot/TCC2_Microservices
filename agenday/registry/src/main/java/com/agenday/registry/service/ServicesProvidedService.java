package com.agenday.registry.service;

import com.agenday.registry.dto.Services.Provided.*;
import com.agenday.registry.model.Employee;
import com.agenday.registry.model.Institution;
import com.agenday.registry.model.ServiceType;
import com.agenday.registry.model.Specialty;
import com.agenday.registry.repository.EmployeeRepository;
import com.agenday.registry.repository.InstitutionRepository;
import com.agenday.registry.repository.ServiceTypeRepository;
import com.agenday.registry.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicesProvidedService {

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public ServicesDTO getServicesDTO() {
        List<ServiceType> serviceTypes = serviceTypeRepository.findAll();
        List<ServiceTypeProvidedDTO> serviceTypeDTOs = new ArrayList<>();

        for (ServiceType serviceType : serviceTypes) {
            ServiceTypeProvidedDTO serviceTypeDTO = new ServiceTypeProvidedDTO();
            serviceTypeDTO.setName(serviceType.getName());

            List<InstitutionProvided> institutionDTOs = new ArrayList<>();
            List<Institution> institutions = institutionRepository.findByServiceTypeId(serviceType.getId());

            for (Institution institution : institutions) {
                InstitutionProvided institutionDTO = new InstitutionProvided();
                institutionDTO.setName(institution.getName());

                List<SpecialtiesProvidedDTO> specialtyDTOs = new ArrayList<>();
                List<Specialty> specialties = specialtyRepository.findByInstitutionId(institution.getId());

                for (Specialty specialty : specialties) {
                    SpecialtiesProvidedDTO specialtyDTO = new SpecialtiesProvidedDTO();
                    specialtyDTO.setName(specialty.getName());

                    List<EmployesProvidedDTO> employeeDTOs = new ArrayList<>();
                    List<Employee> employees = employeeRepository.findByInstitutionIdAndSpecialtyId(institution.getId(), specialty.getId());

                    for (Employee employee : employees) {
                        EmployesProvidedDTO employeeDTO = new EmployesProvidedDTO();
                        employeeDTO.setId(employee.getId());
                        employeeDTO.setName(employee.getName());
                        employeeDTOs.add(employeeDTO);
                    }
                    specialtyDTO.setEmployes(employeeDTOs);
                    specialtyDTOs.add(specialtyDTO);
                }
                institutionDTO.setSpeciaties(specialtyDTOs);
                institutionDTOs.add(institutionDTO);
            }
            serviceTypeDTO.setInstitutions(institutionDTOs);
            serviceTypeDTOs.add(serviceTypeDTO);
        }
        return new ServicesDTO(serviceTypeDTOs);
    }
}
