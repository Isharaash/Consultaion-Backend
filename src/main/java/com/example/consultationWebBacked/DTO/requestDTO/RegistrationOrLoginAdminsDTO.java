package com.example.consultationWebBacked.DTO.requestDTO;

import com.example.consultationWebBacked.entity.Roles;

public record RegistrationOrLoginAdminsDTO(String email,
                                           String name,
                                           String password,
                                           Roles role) {

}
