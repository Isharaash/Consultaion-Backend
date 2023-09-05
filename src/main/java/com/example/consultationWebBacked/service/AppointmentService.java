package com.example.consultationWebBacked.service;

import com.example.consultationWebBacked.DTO.AppointmentDTO;
import com.example.consultationWebBacked.entity.Appointment;
import com.example.consultationWebBacked.repositories.AppointmentRepo;
import com.example.consultationWebBacked.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AppointmentService {
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private ModelMapper modelMapper;


    public String saveAppointment(AppointmentDTO appointmentDTO){
        if (appointmentRepo.existsById(appointmentDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }else {
            appointmentRepo.save(modelMapper.map(appointmentDTO, Appointment.class));
            return VarList.RSP_SUCCESS;
        }
    }

}