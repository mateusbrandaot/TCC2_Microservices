package com.agenday.registry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.agenday.registry.model.Institution;

import java.util.List;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    List<Institution> findByUserId(Long userId);
    List<Institution> findByServiceTypeId(Long serviceTypeId);

}
