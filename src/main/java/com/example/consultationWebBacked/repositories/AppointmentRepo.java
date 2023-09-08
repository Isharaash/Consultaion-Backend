package com.example.consultationWebBacked.repositories;

import com.example.consultationWebBacked.entity.Appointment;
import com.example.consultationWebBacked.entity.Schedule;
import com.example.consultationWebBacked.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {


    Optional<Appointment> findBydate(String date);
    Optional<Appointment> findBycategory(String category);
}
