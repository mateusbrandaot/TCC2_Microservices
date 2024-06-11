package com.agenday.registry.repository;

import com.agenday.registry.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    List<TimeSlot> findByEmployeeId(Long employeeId);

    @Query("SELECT ts FROM TimeSlot ts WHERE ts.employee.id = :employeeId AND ts.dayOfWeek = :dayOfWeek AND ts.startTime = :startTime")
    TimeSlot findByEmployeeIdAndDayOfWeekAndStartTime(@Param("employeeId") Long employeeId, @Param("dayOfWeek") DayOfWeek dayOfWeek, @Param("startTime") LocalTime startTime);

    @Modifying
    @Query("UPDATE TimeSlot t SET t.isAvailable = true")
    void resetAllAvailability();
}
