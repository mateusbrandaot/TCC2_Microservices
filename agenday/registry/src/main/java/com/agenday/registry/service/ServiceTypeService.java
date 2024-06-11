package com.agenday.registry.service;

import com.agenday.registry.model.ServiceType;
import com.agenday.registry.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeService {

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    public List<ServiceType> findAllServiceTypes() {
        return serviceTypeRepository.findAll();
    }

    public ServiceType findServiceTypeById(Long id) {
        return serviceTypeRepository.findById(id).orElse(null);
    }

    public ServiceType saveServiceType(ServiceType serviceType) {
        return serviceTypeRepository.save(serviceType);
    }

    public void deleteServiceType(Long id) {
        serviceTypeRepository.deleteById(id);
    }
}