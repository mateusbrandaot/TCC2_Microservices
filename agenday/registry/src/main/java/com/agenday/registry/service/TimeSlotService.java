package com.agenday.registry.service;

import com.agenday.registry.dto.TimeSlotDTO;
import com.agenday.registry.dto.TimeSlotListDTO;
import com.agenday.registry.model.Employee;
import com.agenday.registry.model.TimeSlot;
import com.agenday.registry.repository.EmployeeRepository;
import com.agenday.registry.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public TimeSlot save(TimeSlotDTO timeSlotDTO) {
        Employee employee = employeeRepository.findById(timeSlotDTO.getEmployeeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime startTime = LocalTime.parse(timeSlotDTO.getStartTime(), formatter);
        LocalTime endTime = LocalTime.parse(timeSlotDTO.getEndTime(), formatter);

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setEmployee(employee);
        timeSlot.setDayOfWeek(timeSlotDTO.getDayOfWeek());
        timeSlot.setStartTime(startTime);
        timeSlot.setEndTime(endTime);
        timeSlot.setAvailable(timeSlotDTO.isAvailable());

        return timeSlotRepository.save(timeSlot);
    }

    public List<TimeSlot> saveAll(TimeSlotListDTO timeSlotListDTO) {
        Employee employee = employeeRepository.findById(timeSlotListDTO.getEmployeeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        List<TimeSlot> timeSlots = timeSlotListDTO.getTimeSlots().stream().map(timeSlotDTO -> {
            LocalTime startTime = LocalTime.parse(timeSlotDTO.getStartTime(), formatter);
            LocalTime endTime = LocalTime.parse(timeSlotDTO.getEndTime(), formatter);

            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setEmployee(employee);
            timeSlot.setDayOfWeek(timeSlotDTO.getDayOfWeek());
            timeSlot.setStartTime(startTime);
            timeSlot.setEndTime(endTime);
            timeSlot.setAvailable(timeSlotDTO.isAvailable());

            return timeSlot;
        }).collect(Collectors.toList());

        return timeSlotRepository.saveAll(timeSlots);
    }

    public List<TimeSlot> findByEmployeeId(Long employeeId) {
        return timeSlotRepository.findByEmployeeId(employeeId);
    }

    public void delete(Long id) {
        timeSlotRepository.deleteById(id);
    }

    @Scheduled(cron = "0 0 0 * * MON")  // Executa toda segunda-feira à meia-noite
    public void resetAvailability() {
        timeSlotRepository.resetAllAvailability();
    }
}
