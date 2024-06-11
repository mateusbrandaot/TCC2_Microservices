package com.agenday.registry.service;

import com.agenday.registry.dto.CityRequestDTO;
import com.agenday.registry.model.City;
import com.agenday.registry.model.State;
import com.agenday.registry.repository.CityRepository;
import com.agenday.registry.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public List<City> findAllCities() {
        return cityRepository.findAll();
    }

    public City findCityById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public City saveCity(CityRequestDTO city)  throws IllegalArgumentException{
        Optional<State> state = stateRepository.findById(city.StateId());
        if(state.isPresent()) {
            return cityRepository.save(City.builder().state(state.get()).name(city.name()).build());
        } else {
            throw  new IllegalArgumentException("Invalid State id");
        }

    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}