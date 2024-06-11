package com.agenday.registry.dto.Services.Provided;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceTypeProvidedDTO {
    private String name;
    private List<InstitutionProvided> institutions;
}
