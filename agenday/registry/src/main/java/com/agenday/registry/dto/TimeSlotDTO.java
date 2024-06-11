package com.agenday.registry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TimeSlotDTO {
    private Long employeeId;
    private DayOfWeek dayOfWeek;
    private String startTime; // Change to String
    private String endTime;
    private boolean isAvailable;


}