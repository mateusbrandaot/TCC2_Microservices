package com.agenday.registry.repository;

import com.agenday.registry.model.Address;
import com.agenday.registry.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    List<Specialty> findByInstitutionId(Long institutionId);
}