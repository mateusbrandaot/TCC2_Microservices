package com.agenday.registry.popula;

import com.agenday.registry.model.City;
import com.agenday.registry.model.ServiceType;
import com.agenday.registry.model.State;
import com.agenday.registry.repository.CityRepository;
import com.agenday.registry.repository.ServiceTypeRepository;
import com.agenday.registry.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PopulateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Transactional
    public void populateStatesAndCities() throws Exception {
        // Verificar se já existem estados cadastrados no banco
        long count = stateRepository.count();
        if (count == 0) { // Se não existirem estados, proceder com a população
            State distritoFederal = new State(null, "Distrito Federal", new ArrayList<>());
            State savedState = stateRepository.save(distritoFederal);
            List<City> cities = new ArrayList<>();
            cities.add(new City(null, "Brasília", savedState, new ArrayList<>()));
            cities.add(new City(null, "Gama", savedState, new ArrayList<>()));
            cities.add(new City(null, "Taguatinga", savedState, new ArrayList<>()));
            cities.add(new City(null, "Sobradinho", savedState, new ArrayList<>()));
            cities.add(new City(null, "Planaltina", savedState, new ArrayList<>()));
            cities.add(new City(null, "Paranoá", savedState, new ArrayList<>()));
            cities.add(new City(null, "Núcleo Bandeirante", savedState, new ArrayList<>()));
            cities.add(new City(null, "Ceilândia", savedState, new ArrayList<>()));
            cities.add(new City(null, "Guará", savedState, new ArrayList<>()));
            cities.add(new City(null, "Cruzeiro", savedState, new ArrayList<>()));
            cities.add(new City(null, "Samambaia", savedState, new ArrayList<>()));
            cities.add(new City(null, "Santa Maria", savedState, new ArrayList<>()));
            cities.add(new City(null, "São Sebastião", savedState, new ArrayList<>()));
            cities.add(new City(null, "Recanto das Emas", savedState, new ArrayList<>()));
            cities.add(new City(null, "Lago Sul", savedState, new ArrayList<>()));
            cities.add(new City(null, "Riacho Fundo", savedState, new ArrayList<>()));
            cities.add(new City(null, "Lago Norte", savedState, new ArrayList<>()));
            cities.add(new City(null, "Candangolândia", savedState, new ArrayList<>()));
            cities.add(new City(null, "Águas Claras", savedState, new ArrayList<>()));
            cities.add(new City(null, "Riacho Fundo II", savedState, new ArrayList<>()));
            cities.add(new City(null, "Sudoeste/Octogonal", savedState, new ArrayList<>()));
            cities.add(new City(null, "Varjão", savedState, new ArrayList<>()));
            cities.add(new City(null, "Park Way", savedState, new ArrayList<>()));
            cities.add(new City(null, "Sobradinho II", savedState, new ArrayList<>()));
            cities.add(new City(null, "Jardim Botânico", savedState, new ArrayList<>()));
            cities.add(new City(null, "Itapoã", savedState, new ArrayList<>()));
            cities.add(new City(null, "SIA", savedState, new ArrayList<>()));
            cities.add(new City(null, "Vicente Pires", savedState, new ArrayList<>()));
            cities.add(new City(null, "Fercal", savedState, new ArrayList<>()));
            // Salvar todas as cidades (regiões administrativas)
            cityRepository.saveAll(cities);
        } else {
            throw new Exception("Banco de dados já contém estados cadastrados.");
        }
    }

    @Transactional
    public void populateServiceTypes() throws Exception {
        long count = serviceTypeRepository.count();
        if(count == 0) {
            List<ServiceType> serviceTypes = List.of(
                    new ServiceType(null, "Saúde", null)
            );
            serviceTypeRepository.saveAll(serviceTypes);
        } else {
            throw new Exception("Banco de dados já contém estados cadastrados.");
        }
    }
}
