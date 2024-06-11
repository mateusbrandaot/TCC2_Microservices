package com.agenday.registry.service;

import com.agenday.registry.exception.ForbiddenException;
import com.agenday.registry.model.Appointment;
import com.agenday.registry.dto.AppointmentDTO;
import com.agenday.registry.model.Client;
import com.agenday.registry.model.Employee;
import com.agenday.registry.model.TimeSlot;
import com.agenday.registry.repository.AppointmentRepository;
import com.agenday.registry.repository.EmployeeRepository;
import com.agenday.registry.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Appointment save(AppointmentDTO appointmentDTO) {
        Employee employee = employeeRepository.findById(appointmentDTO.getEmployeeId())
                .orElseThrow(() -> new ForbiddenException("Error saving institution:"));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDate date = LocalDate.parse(appointmentDTO.getDate(), dateFormatter);
        LocalTime time = LocalTime.parse(appointmentDTO.getTime(), timeFormatter);
        Client client = Client.builder()
                .cpf(appointmentDTO.getClient().getCpf())
                .email(appointmentDTO.getClient().getEmail())
                .name(appointmentDTO.getClient().getName())
                .dateBirth(appointmentDTO.getClient().getDateBirth())
                .phone(appointmentDTO.getClient().getPhone())
                .build();


        // Verificar se o horário está disponível
        TimeSlot timeSlot = timeSlotRepository.findByEmployeeIdAndDayOfWeekAndStartTime(
                employee.getId(),
                date.getDayOfWeek(),
                time
        );

        if (timeSlot == null || !timeSlot.isAvailable()) {
            throw new ForbiddenException("Horario não Dispoível");
        }

        // Marcar o intervalo de tempo como não disponível
        timeSlot.setAvailable(false);
        timeSlotRepository.save(timeSlot);

        Appointment appointment = new Appointment();
        appointment.setEmployee(employee);
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setClient(client);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findByEmployeeId(Long employeeId) {
        return appointmentRepository.findByEmployeeId(employeeId);
    }


}