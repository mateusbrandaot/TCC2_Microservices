package com.agenday.registry.dto;

import com.agenday.registry.model.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionDTO {

    private Long id;

    private String name;

    private String type;

    private String document;

    private Long serviceTypeId;

    private AddressDTO addressDTO;

    private List<ContactDTO> contactDTO;

}
