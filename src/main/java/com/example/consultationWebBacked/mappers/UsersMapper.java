package com.example.consultationWebBacked.mappers;

import com.example.consultationWebBacked.DTO.UsersDTO;
import com.example.consultationWebBacked.DTO.requestDTO.RegistrationOrLoginAdminsDTO;
import com.example.consultationWebBacked.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {
    public Users dtoToEntityForRegOrLogin(RegistrationOrLoginAdminsDTO registrationOrLoginForAdminsDTO) {
        return new Users(
                registrationOrLoginForAdminsDTO.email(),
                registrationOrLoginForAdminsDTO.name(),
                registrationOrLoginForAdminsDTO.password(),
                registrationOrLoginForAdminsDTO.role()
        );
    }
    public UsersDTO EntityToDTO(Users user) {
        return new UsersDTO(
                user.getId(),
                user.getEmail(),
                user.getName()


        );
    }
}
