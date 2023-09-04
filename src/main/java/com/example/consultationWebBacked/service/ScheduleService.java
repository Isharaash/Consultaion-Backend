package com.example.consultationWebBacked.service;

import com.example.consultationWebBacked.DTO.ScheduleDTO;
import com.example.consultationWebBacked.entity.Schedule;
import com.example.consultationWebBacked.repositories.ScheduleRepo;
import com.example.consultationWebBacked.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepo scheduleRepo;

    @Autowired
    private ModelMapper modelMapper;



    public String saveSchedule(ScheduleDTO scheduleDTO){
        if (scheduleRepo.existsById(scheduleDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }else {
            scheduleRepo.save(modelMapper.map(scheduleDTO, Schedule.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public List<ScheduleDTO> getAllSchedule(){
        List<Schedule> ScheduleList = scheduleRepo.findAll();
        return modelMapper.map(ScheduleList,new TypeToken<ArrayList<ScheduleDTO>>(){
        }.getType());
    }

    public ScheduleDTO searchSchedule(int id){
        if (scheduleRepo.existsById(id)){
            Schedule schedule =scheduleRepo.findById(id).orElse(null);
            return modelMapper.map(schedule, ScheduleDTO.class);
        }else {
            return null;
        }
    }
    public String deleteSchedule(int id){
        if (scheduleRepo.existsById(id)){
            scheduleRepo.deleteById(id);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public String updateSchedule(ScheduleDTO scheduleDTO){
        if (scheduleRepo.existsById(scheduleDTO.getId())){
            scheduleRepo.save(modelMapper.map(scheduleDTO, Schedule.class));
            return VarList.RSP_SUCCESS;

        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
