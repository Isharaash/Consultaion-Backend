package com.example.consultationWebBacked.mappers;

import com.example.consultationWebBacked.DTO.TokenDTO;
import com.example.consultationWebBacked.entity.Roles;
import org.springframework.stereotype.Component;

@Component
public class TokenMapper {
    public TokenDTO tokenToDTO(String token, Long id, String email, String name, Roles role){
        return new TokenDTO(
                token,
                id,
                email,
                name,
                role
        );
    }
}
