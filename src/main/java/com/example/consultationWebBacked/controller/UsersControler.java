package com.example.consultationWebBacked.controller;

import com.example.consultationWebBacked.DTO.TokenDTO;
import com.example.consultationWebBacked.DTO.UsersDTO;
import com.example.consultationWebBacked.DTO.requestDTO.RegistrationOrLoginAdminsDTO;
import com.example.consultationWebBacked.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/v1")
public class UsersControler {
    private final UserService userService;

    public UsersControler(UserService userService) {
        this.userService = userService;
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





}
