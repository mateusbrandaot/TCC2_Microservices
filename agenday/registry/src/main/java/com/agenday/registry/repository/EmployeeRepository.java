package com.agenday.registry.repository;

import com.agenday.registry.model.Employee;
import com.agenday.registry.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public List<Employee> findByInstitutionId(Long institutionId);

    @Query("SELECT e FROM Employee e JOIN e.specialties s WHERE e.institution.id = :institutionId AND s.id = :specialtyId")
    List<Employee> findByInstitutionIdAndSpecialtyId(@Param("institutionId") Long institutionId, @Param("specialtyId") Long specialtyId);

}