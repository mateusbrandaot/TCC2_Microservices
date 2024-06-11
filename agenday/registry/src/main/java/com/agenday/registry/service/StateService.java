package com.agenday.registry.service;

import com.agenday.registry.dto.StateDTO;
import com.agenday.registry.dto.StateResponseDTO;
import com.agenday.registry.model.City;
import com.agenday.registry.repository.CityRepository;
import com.agenday.registry.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;


    public List<StateResponseDTO> findAllStates() {
        List<StateResponseDTO > statesListDTO= new ArrayList<>();
        List<com.agenday.registry.model.State> states = stateRepository.findAll();
        for(com.agenday.registry.model.State state : states) {
            StateResponseDTO stateResponseDTO = new StateResponseDTO();
            stateResponseDTO.setId(state.getId());
            stateResponseDTO.setName(state.getName());
            List<City> cities = cityRepository.findByStateId(state.getId());
            stateResponseDTO.setCities(cities);
            statesListDTO.add(stateResponseDTO);
        }
        return statesListDTO;
    }

    public com.agenday.registry.model.State findStateById(Long id) {
        return stateRepository.findById(id).orElse(null);
    }

    public com.agenday.registry.model.State saveStateUpdate(StateDTO stateRequest, long id) {
        com.agenday.registry.model.State state = findStateById(id);
        state.setName(stateRequest.name());
        return stateRepository.save(state);
    }

    public com.agenday.registry.model.State saveState(StateDTO stateRequest) {
        return stateRepository.save(com.agenday.registry.model.State.builder().name(stateRequest.name()).build());
    }

    public void deleteState(Long id) {
        stateRepository.deleteById(id);
    }
}