package com.agenday.registry.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDTO {
    private Long employeeId;
    private String date; // Format: yyyy-MM-dd
    private String time; // Format: HH:mm:ss
    private ClientDTO client;
}