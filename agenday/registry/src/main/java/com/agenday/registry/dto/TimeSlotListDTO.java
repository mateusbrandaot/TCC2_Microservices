package com.agenday.registry.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSlotListDTO {
    private Long employeeId;
    private List<TimeSlotDTO> timeSlots;
}
