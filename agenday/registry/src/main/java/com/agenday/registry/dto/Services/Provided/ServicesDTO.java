package com.agenday.registry.dto.Services.Provided;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicesDTO {
    List<ServiceTypeProvidedDTO> servicesType;
}
