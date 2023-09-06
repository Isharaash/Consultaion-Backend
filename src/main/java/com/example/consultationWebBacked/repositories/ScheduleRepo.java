package com.example.consultationWebBacked.repositories;

import com.example.consultationWebBacked.entity.Schedule;
import com.example.consultationWebBacked.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepo  extends JpaRepository<Schedule,Integer> {

    Optional<Schedule> findByName(String name);
    Optional<Schedule> findBycategory(String category);
}
