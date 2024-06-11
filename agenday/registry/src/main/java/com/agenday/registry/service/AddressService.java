package com.agenday.registry.service;

import com.agenday.registry.dto.AddressDTO;
import com.agenday.registry.model.Address;
import com.agenday.registry.model.City;
import com.agenday.registry.model.Institution;
import com.agenday.registry.model.State;
import com.agenday.registry.repository.AddressRepository;
import com.agenday.registry.repository.CityRepository;
import com.agenday.registry.repository.InstitutionRepository;
import com.agenday.registry.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    public Address findAddressesByIdInstitution(Long id) {
        return addressRepository.findByInstitutionId(id);
    }

    public Address findAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public Address saveAddressUpdate(AddressDTO addressDTO, Long idAddress) throws IllegalArgumentException{
        Optional<Address> addressOptional = addressRepository.findById(idAddress);
        if(addressOptional.isPresent()) {
            Address address = addressOptional.get();
            City city = cityRepository.getById(addressDTO.city());
            address.setCity(city);
            address.setStreet(addressDTO.street());
            address.setZipCode(addressDTO.zipCode());
            address.setComplement(addressDTO.complement());
            address.setNumber(addressDTO.number());
            return addressRepository.save(address);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Address saveAddress(AddressDTO addressRequest, Long institutionId) {
        Optional<City> cityOptional = cityRepository.findById(addressRequest.city());
        Optional<Institution> institution = institutionRepository.findById(institutionId);
        if(cityOptional.isPresent() && institution.isPresent()) {
            Address address = Address.builder()
                    .city(cityOptional.get())
                    .zipCode(addressRequest.zipCode())
                    .institution(institution.get())
                    .street(addressRequest.street())
                    .complement(addressRequest.complement())
                    .number(addressRequest.number())
                    .build();
            return addressRepository.save(address);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}