package com.example.consultationWebBacked.DTO;

import com.example.consultationWebBacked.entity.Roles;

public record TokenDTO(String token,
                       Long id,
                       String email,
                       String name,
                       Roles role) {
}
