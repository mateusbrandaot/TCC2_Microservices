package com.agenday.registry.controller;
import com.agenday.registry.dto.TimeSlotDTO;
import com.agenday.registry.dto.TimeSlotListDTO;
import com.agenday.registry.model.TimeSlot;
import com.agenday.registry.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timeslots")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    // Endpoint para criar uma lista de horários
    @PostMapping
    public ResponseEntity<List<TimeSlot>> createTimeSlots(@RequestBody TimeSlotListDTO timeSlotListDTO) {
        List<TimeSlot> savedTimeSlots = timeSlotService.saveAll(timeSlotListDTO);
        return new ResponseEntity<>(savedTimeSlots, HttpStatus.CREATED);
    }

    // Endpoint para adicionar um único horário
    @PostMapping("/add")
    public ResponseEntity<TimeSlot> addTimeSlot(@RequestBody TimeSlotDTO timeSlotDTO) {
        TimeSlot savedTimeSlot = timeSlotService.save(timeSlotDTO);
        return new ResponseEntity<>(savedTimeSlot, HttpStatus.CREATED);
    }

    // Endpoint para obter todos os horários de um funcionário
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<TimeSlot>> getTimeSlotsByEmployee(@PathVariable Long employeeId) {
        List<TimeSlot> timeSlots = timeSlotService.findByEmployeeId(employeeId);
        return new ResponseEntity<>(timeSlots, HttpStatus.OK);
    }

    // Endpoint para excluir um horário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeSlot(@PathVariable Long id) {
        timeSlotService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
