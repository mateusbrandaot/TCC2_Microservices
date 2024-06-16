package com.agenday.registry.repository;

import com.agenday.registry.model.InstitutionFallback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InstitutionFallbackRepository extends MongoRepository<InstitutionFallback, String> {
}