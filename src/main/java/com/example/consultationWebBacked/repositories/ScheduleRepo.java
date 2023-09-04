package com.example.consultationWebBacked.repositories;

import com.example.consultationWebBacked.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo  extends JpaRepository<Schedule,Integer> {

}
