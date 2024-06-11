package com.agenday.registry.repository;

import com.agenday.registry.model.City;
import com.agenday.registry.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}