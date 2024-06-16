package com.agenday.registry.service;

import com.agenday.registry.dto.ContactDTO;
import com.agenday.registry.dto.InstitutionDTO;
import com.agenday.registry.exception.ForbiddenException;
import com.agenday.registry.model.*;
import com.agenday.registry.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private InstitutionFallbackRepository institutionFallbackRepository;

    private static final Logger logger = LoggerFactory.getLogger(InstitutionService.class);


    public List<Institution> findAllInstitutions() {
        return institutionRepository.findAll();
    }

    public List<InstitutionDTO> findAllInstitutionsByIdUser(long userId) {
        try {
            List<InstitutionDTO> institutionListDTOs = new ArrayList<>();
            List<Institution> institutions = institutionRepository.findByUserId(userId);
            for (Institution institution : institutions) {
                InstitutionDTO institutionDTO = new InstitutionDTO();
                institutionDTO = buildInstitutionDTO(institution);
                institutionListDTOs.add(institutionDTO);
            }
            return institutionListDTOs;
        } catch (Exception e) {
            throw new ForbiddenException("Error saving institution: " + e.getMessage());
        }
    }

    public Institution findInstitutionById(Long id) {
        return institutionRepository.findById(id).orElse(null);
    }

    @CircuitBreaker(name = "institutionService", fallbackMethod = "fallbackSaveInstitution")
    public String saveInstitution(InstitutionDTO institutionDTO, Long idUsuario) {
        try {
            Institution institution = institutionRepository.save(institutionBuild(institutionDTO, idUsuario));
            addressService.saveAddress(institutionDTO.getAddressDTO(), institution.getId());
            for (ContactDTO contact : institutionDTO.getContactDTO()) {
                contactRepository.save(BuilContact(contact, institution.getId()));
            }
            institutionRepository.save(institution);
            return "Institution created successfully";
        } catch (Exception e) {
            logger.error("Error saving institution: ", e);
            throw e;
        }
    }

    public String fallbackSaveInstitution(InstitutionDTO institutionDTO, Long idUsuario, Throwable t) {
        logger.error("Error saving institution. Fallback method activated. Reason: ", t);

        InstitutionFallback fallbackInstitution = new InstitutionFallback();
        fallbackInstitution.setName(institutionDTO.getName());
        fallbackInstitution.setType(institutionDTO.getType());
        fallbackInstitution.setDocument(institutionDTO.getDocument());
        fallbackInstitution.setUserId(idUsuario);
        fallbackInstitution.setServiceTypeId(institutionDTO.getServiceTypeId());
        institutionFallbackRepository.save(fallbackInstitution);
        return "Pending Institution Registration";
    }
    public Institution saveInstitutionUpdate(InstitutionDTO institutionDTO, long institutionId) {
        try {
            Institution institution = institutionRepository.save(institutionBuildUpdate(institutionDTO, institutionId));
            Address address = addressRepository.findByInstitutionId(institutionId);
            addressService.saveAddressUpdate(institutionDTO.getAddressDTO(), address.getId());
            for (ContactDTO contact : institutionDTO.getContactDTO()) {
                contactRepository.save(BuilContact(contact, institution.getId()));
            }
            return institutionRepository.save(institution);
        } catch (Exception e) {
            throw new ForbiddenException("Error updating institution: " + e.getMessage());
        }
    }

    public void deleteInstitution(Long id) {
        try {
            institutionRepository.deleteById(id);
        } catch (Exception e) {
            throw new ForbiddenException("Error deleting institution: " + e.getMessage());
        }
    }

    Institution institutionBuild(InstitutionDTO institutionDTO, Long idUsuario) {
        try {
            Optional<ServiceType> serviceType = serviceTypeRepository.findById(institutionDTO.getServiceTypeId());
            Institution institution = new Institution();
            institution.setName(institutionDTO.getName());
            institution.setType(institutionDTO.getType());
            institution.setDocument(institutionDTO.getDocument());
            institution.setServiceType(serviceType.get());
            User user = userRepository.findById(idUsuario).orElse(null);
            institution.setUser(user);
            return institution;
        } catch (Exception e) {
            throw new ForbiddenException("Error building institution: " + e.getMessage());
        }
    }

    Institution institutionBuildUpdate(InstitutionDTO institutionDTO, Long idInstitution) throws IllegalArgumentException {
        try {
            Optional<Institution> institutionOptional = institutionRepository.findById(idInstitution);
            if (institutionOptional.isPresent()) {
                Institution institution = institutionOptional.get();
                Optional<ServiceType> serviceType = serviceTypeRepository.findById(institutionDTO.getServiceTypeId());
                institution.setName(institutionDTO.getName());
                institution.setType(institutionDTO.getType());
                institution.setDocument(institutionDTO.getDocument());
                institution.setServiceType(serviceType.get());
                User user = userRepository.findById(institution.getUser().getId()).orElse(null);
                institution.setUser(user);
                return institution;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            throw new ForbiddenException("Error updating institution: " + e.getMessage());
        }
    }

    public Contact BuilContact(ContactDTO contactDTO, Long institutionId) {
        try {
            Contact contact = new Contact();
            contact.setType(contactDTO.getType());
            contact.setValue(contactDTO.getValue());
            Institution institution = institutionRepository.getById(institutionId);
            contact.setInstitution(institution);
            return contact;
        } catch (Exception e) {
            throw new ForbiddenException("Error building contact: " + e.getMessage());
        }
    }

    public Contact BuilContactUpdate(ContactDTO contactDTO, Long institutionId, long idContact) {
        try {
            Contact contact = contactRepository.getById(idContact);
            contact.setType(contactDTO.getType());
            contact.setValue(contactDTO.getValue());
            Institution institution = institutionRepository.getById(institutionId);
            contact.setInstitution(institution);
            return contact;
        } catch (Exception e) {
            throw new ForbiddenException("Error updating contact: " + e.getMessage());
        }
    }

    public InstitutionDTO buildInstitutionDTO(Institution institution) {
        InstitutionDTO institutionDTO = new InstitutionDTO();
        List<ContactDTO> contactListDTO = new ArrayList<>();
        institutionDTO.setId(institution.getId());
        institutionDTO.setName(institution.getName());
        institutionDTO.setType(institution.getType());
        institutionDTO.setDocument(institution.getDocument());
        institutionDTO.setServiceTypeId(institution.getServiceType().getId());
        for (Contact contact : institution.getContacts()) {
            ContactDTO contactDTO = new ContactDTO();
            contactDTO.setValue(contact.getValue());
            contactDTO.setType(contact.getType());
            contactListDTO.add(contactDTO);
        }
        institutionDTO.setContactDTO(contactListDTO);
        return institutionDTO;
    }
}

