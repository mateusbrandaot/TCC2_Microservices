package com.agenday.registry.repository;

import com.agenday.registry.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByInstitutionId(Long institutionId);
}
