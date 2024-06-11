package com.agenday.registry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private Long institutionId;
    private List<Long> specialtyIds; // for request
    private List<SpecialtyDTO> specialties; // for response


}
