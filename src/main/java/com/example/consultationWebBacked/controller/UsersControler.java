package com.example.consultationWebBacked.controller;

import com.example.consultationWebBacked.DTO.ResponseDTO;
import com.example.consultationWebBacked.DTO.ScheduleDTO;
import com.example.consultationWebBacked.DTO.TokenDTO;
import com.example.consultationWebBacked.DTO.UsersDTO;
import com.example.consultationWebBacked.DTO.requestDTO.RegistrationOrLoginAdminsDTO;
import com.example.consultationWebBacked.service.ScheduleService;
import com.example.consultationWebBacked.service.UserService;
import com.example.consultationWebBacked.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/v1")
public class UsersControler {
    @Autowired
    private ResponseDTO responseDTO;
    private final UserService userService;
    private final ScheduleService scheduleService;
    public UsersControler(UserService userService, ScheduleService scheduleService) {
        this.userService = userService;
        this.scheduleService = scheduleService;
    }

    @PostMapping(path = "/create")
    public TokenDTO createUsers(@RequestBody RegistrationOrLoginAdminsDTO registrationOrLoginAdminsDTO) {
        return userService.createAdmin(registrationOrLoginAdminsDTO);
    }

    @PostMapping(path = "/login")
    public TokenDTO loginUsers(@RequestBody RegistrationOrLoginAdminsDTO registrationOrLoginAdminsDTO) {
        System.out.println(registrationOrLoginAdminsDTO.email());
        return userService.loginAdmin(registrationOrLoginAdminsDTO);
    }


    @GetMapping(path = "/all")
    public List<UsersDTO> getAllUsers() {
        return userService.getAllAdmins();
    }

    @PostMapping(value = "/saveSchedule")
    public ResponseEntity saveSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        try {
            String res = scheduleService.saveSchedule(scheduleDTO);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(scheduleDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Employee Registered");
                responseDTO.setContent(scheduleDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/AllSchedulet")
    public ResponseEntity getAllSchedulet() {
        try {
            List<ScheduleDTO> scheduleDTOList = scheduleService.getAllSchedule();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(scheduleDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/searchSchedulet/{id}")
    public ResponseEntity searchSchedule(@PathVariable int id) {
        try {
            ScheduleDTO scheduleDTO = scheduleService.searchSchedule(id);
            if (scheduleDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(scheduleDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateSchedulet")
    public ResponseEntity updateSchedulet(@RequestBody ScheduleDTO scheduleDTO) {
        try {
            String res = scheduleService.updateSchedule(scheduleDTO);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(scheduleDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("01")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Not A Registered Employee");
                responseDTO.setContent(scheduleDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @DeleteMapping("/deleteSchedulet/{id}")
    public ResponseEntity deleteSchedulet(@PathVariable int id) {
        try {
            String res = scheduleService.deleteSchedule(id);
            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
