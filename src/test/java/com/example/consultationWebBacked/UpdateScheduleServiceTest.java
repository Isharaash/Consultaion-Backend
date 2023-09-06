package com.example.consultationWebBacked;

import com.example.consultationWebBacked.DTO.ScheduleDTO;
import com.example.consultationWebBacked.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UpdateScheduleServiceTest {
    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void testupdateSchedule() {

        ScheduleDTO scheduleDTO=new ScheduleDTO();
        scheduleDTO.setId(1);
        scheduleDTO.setName("John");
        scheduleDTO.setDate("2023-09-07");
        scheduleDTO.setDay("Monday");
        scheduleDTO.setFtime("08:00 AM");
        scheduleDTO.setTtime("04:00 PM");
        scheduleDTO.setCategory("Test Category");

        int result = Integer.parseInt(scheduleService.updateSchedule(scheduleDTO));

        assertEquals(20, result);
    }
}
