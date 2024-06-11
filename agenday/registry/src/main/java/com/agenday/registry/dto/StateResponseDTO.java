package com.agenday.registry.dto;

import com.agenday.registry.model.City;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateResponseDTO {
    long id;
    String name;
    List<City> cities;
}
