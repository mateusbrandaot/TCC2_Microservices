package com.agenday.registry.controller;

import com.agenday.registry.model.TimeSlot;
import com.agenday.registry.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees/{employeeId}/timeslots")
public class EmployeeTimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @GetMapping
    public ResponseEntity<List<TimeSlot>> getTimeSlots(@PathVariable Long employeeId) {
        List<TimeSlot> timeSlots = timeSlotService.findByEmployeeId(employeeId);
        return new ResponseEntity<>(timeSlots, HttpStatus.OK);
    }
}