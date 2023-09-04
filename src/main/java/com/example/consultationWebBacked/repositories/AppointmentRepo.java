package com.example.consultationWebBacked.repositories;

import com.example.consultationWebBacked.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {
}
