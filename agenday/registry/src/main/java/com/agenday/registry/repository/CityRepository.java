package com.agenday.registry.repository;

import com.agenday.registry.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {


    List<City> findByStateId(Long id);

}