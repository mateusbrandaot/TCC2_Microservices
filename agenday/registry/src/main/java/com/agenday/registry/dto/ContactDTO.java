package com.agenday.registry.dto;

import com.agenday.registry.model.Institution;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDTO {

    private String type; // e.g., Email, Phone, Fax

    private String value;

}
