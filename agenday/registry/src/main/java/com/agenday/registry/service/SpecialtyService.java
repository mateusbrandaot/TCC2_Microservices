package com.agenday.registry.service;

import com.agenday.registry.dto.SpecialtyDTO;
import com.agenday.registry.exception.ForbiddenException;
import com.agenday.registry.model.*;
import com.agenday.registry.repository.InstitutionRepository;
import com.agenday.registry.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    public List<Specialty> findAddressesByIdInstitution(Long id) {
        try {
            return specialtyRepository.findByInstitutionId(id);
        } catch (Exception e) {
            throw new ForbiddenException("Error found specialties: " + e.getMessage());
        }
    }

    public Specialty saveSpecialty(SpecialtyDTO specialtyDTO, Long institutionId) throws IllegalArgumentException {
        try {
            Institution institution = institutionRepository.getById(institutionId);
            Specialty specialty = new Specialty();
            specialty.setInstitution(institution);
            specialty.setDescription(specialtyDTO.getDescription());
            specialty.setName(specialtyDTO.getName());
            return specialtyRepository.save(specialty);
        } catch (Exception e) {
            throw new ForbiddenException("Error save specialty: " + e.getMessage());
        }
    }

    public Specialty saveSpecialtyUpdate(SpecialtyDTO specialtyDTO, Long idSpecialt) throws IllegalArgumentException{
        Optional<Specialty> specialtyptional = specialtyRepository.findById(idSpecialt);
        if(specialtyptional.isPresent()) {
            Specialty specialty = specialtyptional.get();
            specialty.setDescription(specialtyDTO.getDescription());
            specialty.setName(specialtyDTO.getName());
            return specialtyRepository.save(specialty);
        } else {
            throw new ForbiddenException("Error update specialty ");
        }
    }

    public void deleteSpecialty(Long id) {
        try {
            specialtyRepository.deleteById(id);
        } catch (Exception e){
            throw new ForbiddenException("Error delete specialty");
        }
    }
}