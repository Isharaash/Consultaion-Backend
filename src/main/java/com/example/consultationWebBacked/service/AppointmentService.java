package com.example.consultationWebBacked.service;

import com.example.consultationWebBacked.DTO.AppointmentDTO;
import com.example.consultationWebBacked.DTO.ScheduleDTO;
import com.example.consultationWebBacked.entity.Appointment;
import com.example.consultationWebBacked.entity.Schedule;
import com.example.consultationWebBacked.repositories.AppointmentRepo;
import com.example.consultationWebBacked.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<AppointmentDTO> getAllAppointment(){
        List<Appointment> employeeList = appointmentRepo.findAll();
        return modelMapper.map(employeeList,new TypeToken<ArrayList<AppointmentDTO>>(){
        }.getType());
    }
    public AppointmentDTO searchAppointment(int id){
        if (appointmentRepo.existsById(id)){
            Appointment employee =appointmentRepo.findById(id).orElse(null);
            return modelMapper.map(employee, AppointmentDTO.class);
        }else {
            return null;
        }
    }
    public AppointmentDTO searchAppointment2(String category ){
        Optional<Appointment> appointmentOptional = appointmentRepo.findBycategory(category);

        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            return modelMapper.map(appointment, AppointmentDTO.class);
        } else {
            return null;
        }
    }
    public AppointmentDTO searchAppointment3(String date ){
        Optional<Appointment> appointmentOptional = appointmentRepo.findBydate(date);

        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            return modelMapper.map(appointment, AppointmentDTO.class);
        } else {
            return null;
        }
    }


    public String deleteAppointment(int id){
        if (appointmentRepo.existsById(id)){
            appointmentRepo.deleteById(id);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public List<Appointment> getallAppointmen() {
        return appointmentRepo.findAll();
    }
}
