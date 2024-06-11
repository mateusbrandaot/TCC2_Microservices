package com.agenday.registry.dto;

import jakarta.persistence.Column;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String name;
    private String phone;
    private String email;
    private String cpf;
    private String dateBirth;
}
