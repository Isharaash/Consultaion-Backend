package com.example.consultationWebBacked.repositories;

import com.example.consultationWebBacked.entity.Appointment;
import com.example.consultationWebBacked.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {
}
